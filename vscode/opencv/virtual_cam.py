import sys
import cv2
import numpy as np

from PyQt5.QtWidgets import (
    QApplication, QWidget, QLabel, QPushButton,
    QVBoxLayout, QHBoxLayout, QSlider, QCheckBox,
    QLineEdit, QFileDialog, QGroupBox, QSpinBox
)
from PyQt5.QtGui import QPixmap, QImage
from PyQt5.QtCore import Qt, QTimer


class VirtualCam(QWidget):

    def __init__(self):
        super().__init__()
        self.setWindowTitle("Virtual Cam Filters")

        # ── 웹캠 ──────────────────────────────────
        self.cap = cv2.VideoCapture(0)

        # ── 얼굴 감지 (Haar Cascade) ──────────────
        # mediapipe 대신 OpenCV 내장 모델 사용
        self.face_cascade = cv2.CascadeClassifier(
            cv2.data.haarcascades + 'haarcascade_frontalface_default.xml'
        )
        self.eye_cascade = cv2.CascadeClassifier(
            cv2.data.haarcascades + 'haarcascade_eye.xml'
        )

        # ── 배경 분리용 MOG2 (mediapipe Segmentation 대체) ──
        # 움직임 기반이 아니라 GrabCut 방식으로 대체
        self.bg_subtractor = cv2.createBackgroundSubtractorMOG2(
            history=500, varThreshold=50, detectShadows=False
        )
        self.bg_initialized = False

        # ── 나이/성별 모델 로드 ───────────────────
        try:
            self.face_net   = cv2.dnn.readNet("opencv_face_detector_uint8.pb",
                                              "opencv_face_detector.pbtxt")
            self.age_net    = cv2.dnn.readNet("age_net.caffemodel",
                                              "age_deploy.prototxt")
            self.gender_net = cv2.dnn.readNet("gender_net.caffemodel",
                                              "gender_deploy.prototxt")
            self.age_gender_loaded = True
        except:
            self.age_gender_loaded = False

        self.AGE_LIST    = ['(0-2)','(4-6)','(8-12)','(15-20)',
                            '(25-32)','(38-43)','(48-53)','(60-100)']
        self.GENDER_LIST = ['Male', 'Female']

        # ── 선글라스 / 모자 ───────────────────────
        self.sunglass_img = cv2.imread("sunglass.png", cv2.IMREAD_UNCHANGED)
        self.hat_img      = cv2.imread("hat.png",      cv2.IMREAD_UNCHANGED)

        # ── 가상 배경 ─────────────────────────────
        self.virtual_bg = None

        # ── FPS ───────────────────────────────────
        self.prev_tick = cv2.getTickCount()
        self.fps = 0.0

        # ── 텍스트 오버레이 ───────────────────────
        self.overlays = []

        # ── 배경 학습용 프레임 카운터 ─────────────
        self.frame_count = 0

        self._build_ui()

        self.timer = QTimer()
        self.timer.timeout.connect(self.update_frame)
        self.timer.start(33)

    # ==========================================================
    def _build_ui(self):
        self.video_label = QLabel()
        self.video_label.setFixedSize(640, 480)

        cb_group = QGroupBox("필터 ON/OFF")
        cb_layout = QVBoxLayout()

        self.cb_mirror    = QCheckBox("좌우반전 (셀카모드)")
        self.cb_soft      = QCheckBox("뽀샤시 필터")
        self.cb_beauty    = QCheckBox("뷰티 필터 (눈·코·얼굴형)")
        self.cb_bg_bw     = QCheckBox("배경 흑백 + 나만 컬러")
        self.cb_bg_blur   = QCheckBox("배경 흐리기 (버추얼 줌)")
        self.cb_bg_change = QCheckBox("가상 배경 교체")
        self.cb_sunglass  = QCheckBox("가상 선글라스")
        self.cb_hat       = QCheckBox("가상 모자")
        self.cb_age       = QCheckBox("나이/성별 예측")
        self.cb_fps       = QCheckBox("FPS 표시")

        for cb in [self.cb_mirror, self.cb_soft, self.cb_beauty,
                   self.cb_bg_bw, self.cb_bg_blur, self.cb_bg_change,
                   self.cb_sunglass, self.cb_hat, self.cb_age, self.cb_fps]:
            cb_layout.addWidget(cb)
        cb_group.setLayout(cb_layout)

        soft_group = QGroupBox("뽀샤시 강도")
        soft_layout = QVBoxLayout()
        self.soft_slider = QSlider(Qt.Horizontal)
        self.soft_slider.setMinimum(1)
        self.soft_slider.setMaximum(20)
        self.soft_slider.setValue(5)
        soft_layout.addWidget(self.soft_slider)
        soft_group.setLayout(soft_layout)

        beauty_group = QGroupBox("뷰티 조절")
        beauty_layout = QVBoxLayout()
        beauty_layout.addWidget(QLabel("눈 키우기"))
        self.eye_slider = QSlider(Qt.Horizontal)
        self.eye_slider.setMinimum(0)
        self.eye_slider.setMaximum(20)
        self.eye_slider.setValue(0)
        beauty_layout.addWidget(self.eye_slider)
        beauty_layout.addWidget(QLabel("콧볼 줄이기"))
        self.nose_slider = QSlider(Qt.Horizontal)
        self.nose_slider.setMinimum(0)
        self.nose_slider.setMaximum(20)
        self.nose_slider.setValue(0)
        beauty_layout.addWidget(self.nose_slider)
        beauty_layout.addWidget(QLabel("얼굴형 줄이기"))
        self.face_slim_slider = QSlider(Qt.Horizontal)
        self.face_slim_slider.setMinimum(0)
        self.face_slim_slider.setMaximum(20)
        self.face_slim_slider.setValue(0)
        beauty_layout.addWidget(self.face_slim_slider)
        beauty_group.setLayout(beauty_layout)

        text_group = QGroupBox("텍스트 오버레이")
        text_layout = QVBoxLayout()
        self.text_input = QLineEdit()
        self.text_input.setPlaceholderText("화면에 넣을 텍스트 입력")
        pos_layout = QHBoxLayout()
        pos_layout.addWidget(QLabel("X:"))
        self.text_x = QSpinBox()
        self.text_x.setMaximum(1920)
        self.text_x.setValue(50)
        pos_layout.addWidget(self.text_x)
        pos_layout.addWidget(QLabel("Y:"))
        self.text_y = QSpinBox()
        self.text_y.setMaximum(1080)
        self.text_y.setValue(50)
        pos_layout.addWidget(self.text_y)
        add_text_btn   = QPushButton("텍스트 추가")
        clear_text_btn = QPushButton("전체 삭제")
        add_text_btn.clicked.connect(self.add_text_overlay)
        clear_text_btn.clicked.connect(lambda: self.overlays.clear())
        text_layout.addWidget(self.text_input)
        text_layout.addLayout(pos_layout)
        text_layout.addWidget(add_text_btn)
        text_layout.addWidget(clear_text_btn)
        text_group.setLayout(text_layout)

        bg_btn = QPushButton("가상 배경 이미지 선택")
        bg_btn.clicked.connect(self.load_bg_image)

        # 배경 초기화 버튼 (MOG2용 - 자리 바꿀 때 눌러주세요)
        reset_bg_btn = QPushButton("배경 다시 학습")
        reset_bg_btn.clicked.connect(self._reset_bg)

        right_panel = QVBoxLayout()
        right_panel.addWidget(cb_group)
        right_panel.addWidget(soft_group)
        right_panel.addWidget(beauty_group)
        right_panel.addWidget(text_group)
        right_panel.addWidget(bg_btn)
        right_panel.addWidget(reset_bg_btn)
        right_panel.addStretch()

        main_layout = QHBoxLayout()
        main_layout.addWidget(self.video_label)
        main_layout.addLayout(right_panel)
        self.setLayout(main_layout)

    # ==========================================================
    def update_frame(self):
        ret, frame = self.cap.read()
        if not ret:
            return

        tick = cv2.getTickCount()
        self.fps = cv2.getTickFrequency() / (tick - self.prev_tick)
        self.prev_tick = tick
        self.frame_count += 1

        # MOG2 배경 학습 (처음 60프레임은 항상 학습)
        fg_mask = self.bg_subtractor.apply(frame)
        if self.frame_count < 60:
            self._show_frame(frame)
            return

        # 1. 좌우반전
        if self.cb_mirror.isChecked():
            frame = cv2.flip(frame, 1)
            fg_mask = cv2.flip(fg_mask, 1)

        # 2. 뽀샤시
        if self.cb_soft.isChecked():
            frame = self._apply_soft(frame)

        # 배경 마스크 (MOG2 기반)
        need_mask = any([self.cb_bg_bw.isChecked(),
                         self.cb_bg_blur.isChecked(),
                         self.cb_bg_change.isChecked()])
        if need_mask:
            mask = self._get_fg_mask(fg_mask)

        # 3. 뷰티 필터
        if self.cb_beauty.isChecked():
            frame = self._apply_beauty(frame)

        # 4. 배경 흑백
        if self.cb_bg_bw.isChecked():
            frame = self._apply_bg_bw(frame, mask)

        # 5. 배경 흐리기
        if self.cb_bg_blur.isChecked():
            frame = self._apply_bg_blur(frame, mask)

        # 6. 가상 배경
        if self.cb_bg_change.isChecked():
            frame = self._apply_bg_change(frame, mask)

        # 7. 선글라스
        if self.cb_sunglass.isChecked() and self.sunglass_img is not None:
            frame = self._apply_accessory(frame, self.sunglass_img, "sunglass")

        # 8. 모자
        if self.cb_hat.isChecked() and self.hat_img is not None:
            frame = self._apply_accessory(frame, self.hat_img, "hat")

        # 9. 나이/성별
        if self.cb_age.isChecked() and self.age_gender_loaded:
            frame = self._apply_age_gender(frame)

        # 10. FPS
        if self.cb_fps.isChecked():
            cv2.putText(frame, f"FPS: {self.fps:.1f}",
                        (10, 30), cv2.FONT_HERSHEY_SIMPLEX,
                        1, (0, 255, 0), 2)

        # 11. 텍스트 오버레이
        for ov in self.overlays:
            cv2.putText(frame, ov["text"], (ov["x"], ov["y"]),
                        cv2.FONT_HERSHEY_SIMPLEX, 1.2, ov["color"], 2)

        self._show_frame(frame)

    # ==========================================================
    def _apply_soft(self, frame):
        val = self.soft_slider.value()
        ksize = val * 2 + 1
        blurred = cv2.GaussianBlur(frame, (ksize, ksize), 0)
        alpha = 0.3 + val * 0.02
        return cv2.addWeighted(frame, 1 - alpha, blurred, alpha, 0)

    def _get_fg_mask(self, fg_mask_raw):
        """MOG2 마스크 정리 → 사람 영역 마스크"""
        # 노이즈 제거
        kernel = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (5, 5))
        mask = cv2.morphologyEx(fg_mask_raw, cv2.MORPH_CLOSE, kernel)
        mask = cv2.morphologyEx(mask, cv2.MORPH_OPEN, kernel)
        # 경계 부드럽게
        mask = cv2.GaussianBlur(mask, (21, 21), 0)
        return mask

    def _apply_bg_bw(self, frame, mask):
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        gray_3ch = cv2.cvtColor(gray, cv2.COLOR_GRAY2BGR)
        m = mask[:, :, np.newaxis] / 255.0
        return (frame * m + gray_3ch * (1 - m)).astype(np.uint8)

    def _apply_bg_blur(self, frame, mask):
        blurred_bg = cv2.GaussianBlur(frame, (55, 55), 0)
        m = mask[:, :, np.newaxis] / 255.0
        return (frame * m + blurred_bg * (1 - m)).astype(np.uint8)

    def _apply_bg_change(self, frame, mask):
        h, w = frame.shape[:2]
        if self.virtual_bg is not None:
            bg = cv2.resize(self.virtual_bg, (w, h))
        else:
            bg = np.zeros_like(frame)
            bg[:] = (0, 200, 0)
        m = mask[:, :, np.newaxis] / 255.0
        return (frame * m + bg * (1 - m)).astype(np.uint8)

    def _apply_beauty(self, frame):
        """Haar Cascade로 얼굴/눈 감지 후 warp"""
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        faces = self.face_cascade.detectMultiScale(gray, 1.1, 5, minSize=(80, 80))
        if len(faces) == 0:
            return frame

        output = frame.copy()
        eye_strength  = self.eye_slider.value()  * 0.003
        nose_strength = self.nose_slider.value() * 0.002
        slim_strength = self.face_slim_slider.value() * 0.002

        for (fx, fy, fw, fh) in faces:
            # 얼굴형 줄이기 (양 광대 위치 추정)
            if slim_strength > 0:
                for cx, cy in [(fx, fy + fh//2), (fx + fw, fy + fh//2)]:
                    r = int(fw * 0.3)
                    output = self._bulge_warp(output, cx, cy, r, -slim_strength)

            # 눈 크게 (눈 영역 감지)
            if eye_strength > 0:
                face_gray = gray[fy:fy+fh, fx:fx+fw]
                eyes = self.eye_cascade.detectMultiScale(face_gray, 1.1, 3)
                for (ex, ey, ew, eh) in eyes[:2]:
                    cx = fx + ex + ew // 2
                    cy = fy + ey + eh // 2
                    r = int(fw * 0.12)
                    output = self._bulge_warp(output, cx, cy, r, eye_strength)

            # 코 위치 추정 (얼굴 중앙 아래쪽)
            if nose_strength > 0:
                cx = fx + fw // 2
                cy = fy + int(fh * 0.6)
                r = int(fw * 0.15)
                output = self._bulge_warp(output, cx, cy, r, -nose_strength)

        return output

    def _bulge_warp(self, img, cx, cy, radius, strength):
        h, w = img.shape[:2]
        x1 = max(cx - radius, 0)
        y1 = max(cy - radius, 0)
        x2 = min(cx + radius, w)
        y2 = min(cy + radius, h)
        region = img[y1:y2, x1:x2].copy()
        rh, rw = region.shape[:2]
        if rh == 0 or rw == 0:
            return img

        # numpy 벡터 연산으로 for문 대체 (빠름!)
        ys, xs = np.mgrid[0:rh, 0:rw].astype(np.float32)
        dx = xs - (cx - x1)
        dy = ys - (cy - y1)
        dist = np.sqrt(dx * dx + dy * dy)
        factor = np.where(
            (dist < radius) & (dist > 0),
            1 - strength * np.exp(-(dist * dist) / (2 * (radius * 0.5) ** 2)),
            1.0
        )
        map_x = np.clip((cx - x1) + dx * factor, 0, rw - 1).astype(np.float32)
        map_y = np.clip((cy - y1) + dy * factor, 0, rh - 1).astype(np.float32)

        warped = cv2.remap(region, map_x, map_y, cv2.INTER_LINEAR)
        result = img.copy()
        result[y1:y2, x1:x2] = warped
        return result

    def _apply_accessory(self, frame, accessory_img, acc_type):
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        faces = self.face_cascade.detectMultiScale(gray, 1.1, 5, minSize=(80, 80))
        if len(faces) == 0:
            return frame

        output = frame.copy()
        for (fx, fy, fw, fh) in faces:
            if acc_type == "sunglass":
                acc_w = fw
                acc_h = int(fh * 0.25)
                acc_x = fx
                acc_y = fy + int(fh * 0.30)
            else:
                acc_w = int(fw * 1.3)
                acc_h = int(fh * 0.5)
                acc_x = fx - int(fw * 0.15)
                acc_y = fy - int(fh * 0.45)
            output = self._overlay_png(output, accessory_img, acc_x, acc_y, acc_w, acc_h)
        return output

    def _overlay_png(self, bg, overlay_img, x, y, target_w, target_h):
        if target_w <= 0 or target_h <= 0:
            return bg
        overlay = cv2.resize(overlay_img, (target_w, target_h))
        h, w = bg.shape[:2]
        x1, y1 = max(x, 0), max(y, 0)
        x2, y2 = min(x + target_w, w), min(y + target_h, h)
        ox1, oy1 = x1 - x, y1 - y
        ox2, oy2 = ox1 + (x2 - x1), oy1 + (y2 - y1)
        if x1 >= x2 or y1 >= y2:
            return bg
        roi = bg[y1:y2, x1:x2]
        ov  = overlay[oy1:oy2, ox1:ox2]
        if ov.shape[2] == 4:
            alpha = ov[:, :, 3:4] / 255.0
            color = ov[:, :, :3]
            bg[y1:y2, x1:x2] = (color * alpha + roi * (1 - alpha)).astype(np.uint8)
        else:
            bg[y1:y2, x1:x2] = ov[:, :, :3]
        return bg

    def _apply_age_gender(self, frame):
        h, w = frame.shape[:2]
        blob = cv2.dnn.blobFromImage(frame, 1.0, (300, 300),
                                     [104, 117, 123], True, False)
        self.face_net.setInput(blob)
        detections = self.face_net.forward()
        for i in range(detections.shape[2]):
            conf = detections[0, 0, i, 2]
            if conf < 0.7:
                continue
            x1 = int(detections[0, 0, i, 3] * w)
            y1 = int(detections[0, 0, i, 4] * h)
            x2 = int(detections[0, 0, i, 5] * w)
            y2 = int(detections[0, 0, i, 6] * h)
            pad = 20
            face_roi = frame[max(0,y1-pad):min(h,y2+pad),
                             max(0,x1-pad):min(w,x2+pad)]
            if face_roi.size == 0:
                continue
            face_blob = cv2.dnn.blobFromImage(
                face_roi, 1.0, (227, 227),
                [78.4263377603, 87.7689143744, 114.895847746], swapRB=False)
            self.gender_net.setInput(face_blob)
            gender = self.GENDER_LIST[self.gender_net.forward()[0].argmax()]
            self.age_net.setInput(face_blob)
            age = self.AGE_LIST[self.age_net.forward()[0].argmax()]
            label = f"{gender}, {age}"
            cv2.rectangle(frame, (x1, y1), (x2, y2), (0, 255, 255), 2)
            cv2.putText(frame, label, (x1, y1 - 10),
                        cv2.FONT_HERSHEY_SIMPLEX, 0.8, (0, 255, 255), 2)
        return frame

    def add_text_overlay(self):
        text = self.text_input.text().strip()
        if text:
            self.overlays.append({
                "text": text, "x": self.text_x.value(),
                "y": self.text_y.value(), "color": (0, 255, 255)
            })
            self.text_input.clear()

    def load_bg_image(self):
        path, _ = QFileDialog.getOpenFileName(
            self, "배경 이미지 선택", "", "Images (*.jpg *.png *.bmp)")
        if path:
            self.virtual_bg = cv2.imread(path)

    def _reset_bg(self):
        """배경 재학습 - 자리 이동하거나 배경 바뀌었을 때 누르기"""
        self.bg_subtractor = cv2.createBackgroundSubtractorMOG2(
            history=500, varThreshold=50, detectShadows=False)
        self.frame_count = 0

    def _show_frame(self, frame):
        rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
        h, w, ch = rgb.shape
        qt_img = QImage(rgb.data, w, h, ch * w, QImage.Format_RGB888)
        self.video_label.setPixmap(QPixmap.fromImage(qt_img))

    def closeEvent(self, event):
        self.timer.stop()
        self.cap.release()
        event.accept()


app = QApplication(sys.argv)
window = VirtualCam()
window.show()
sys.exit(app.exec_())