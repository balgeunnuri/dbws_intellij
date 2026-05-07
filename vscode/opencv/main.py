# =============================================
# 필요한 도구들을 가져오는 곳
# 레고 블록처럼, 미리 만들어진 기능들을 불러와요
# =============================================

import sys          # 프로그램을 끄는 기능
import cv2          # 사진을 다루는 기능 (OpenCV)
import numpy as np  # 숫자 계산을 도와주는 기능 (세피아 필터에서 사용)

# 창(window) 만드는 부품들을 가져와요
from PyQt5.QtWidgets import (
    QApplication,  # 앱 전체를 관리하는 큰 틀
    QWidget,       # 빈 창 하나
    QLabel,        # 글자나 사진을 보여주는 액자
    QPushButton,   # 누를 수 있는 버튼
    QVBoxLayout,   # 부품들을 위아래로 쌓는 정렬
    QHBoxLayout,   # 부품들을 옆으로 나란히 정렬
    QSlider,       # 좌우로 드래그하는 슬라이더 바
    QFileDialog    # "파일 열기/저장" 팝업창
)
from PyQt5.QtGui import QPixmap, QImage  # 사진을 창에 그리는 도구
from PyQt5.QtCore import Qt              # 슬라이더 방향 같은 기본 설정값


# =============================================
# PhotoFilter = 우리가 만드는 앱 전체
# QWidget을 상속 = 빈 창에서 시작해서 기능을 추가해요
# =============================================
class PhotoFilter(QWidget):

    def __init__(self):
        # __init__ = 앱이 처음 켜질 때 딱 한 번 실행되는 준비 작업
        super().__init__()  # 부모 클래스(QWidget)의 준비도 같이 해줘요

        self.setWindowTitle("Image Filter")  # 창 제목 설정

        # ── 사진 불러오기 ─────────────────────────
        # cv2.imread = 파일에서 사진을 읽어서 숫자 배열로 저장
        # 사진 = 가로x세로 픽셀이 각각 (파랑, 초록, 빨강) 숫자로 저장된 것
        self.original = cv2.imread("test.jpg")  # 원본 (절대 건드리지 않을 보관용)
        self.current  = self.original.copy()    # 현재 보여줄 사진 (필터 적용용)

        # ── 사진을 보여줄 액자 ─────────────────────
        # QLabel = 텅 빈 액자. 나중에 사진을 끼워 넣을 거예요
        self.image_label = QLabel()

        # ── 버튼 13개 만들기 ──────────────────────
        # QPushButton("텍스트") = 그 글자가 적힌 버튼 하나 생성
        self.normal_btn   = QPushButton("원본")    # 원래 사진으로 되돌리기
        self.gray_btn     = QPushButton("흑백")    # 색을 없애서 흑백으로
        self.blur_btn     = QPushButton("블러")    # 뿌옇게 흐리게
        self.edge_btn     = QPushButton("엣지")    # 윤곽선만 남기기
        self.bright_btn   = QPushButton("밝게")    # 전체적으로 밝게
        self.dark_btn     = QPushButton("어둡게")  # 전체적으로 어둡게
        self.flip_btn     = QPushButton("좌우반전") # 거울처럼 뒤집기
        self.sepia_btn    = QPushButton("세피아")  # 옛날 사진 느낌 갈색톤
        self.rotate_btn   = QPushButton("회전")    # 90도 돌리기
        self.face_btn     = QPushButton("얼굴인식") # 얼굴에 초록 박스 그리기
        self.equalize_btn = QPushButton("밝기보정") # 어두운 사진 자동 보정
        self.open_btn     = QPushButton("열기")    # 다른 사진 파일 불러오기
        self.save_btn     = QPushButton("저장")    # 지금 화면 사진 저장

        # ── 블러 슬라이더 만들기 ──────────────────
        # QSlider = 동그란 손잡이를 좌우로 밀 수 있는 바
        self.blur_slider = QSlider(Qt.Horizontal)  # 가로 방향 슬라이더
        self.blur_slider.setMinimum(1)   # 왼쪽 끝 = 1 (거의 안 흐림)
        self.blur_slider.setMaximum(30)  # 오른쪽 끝 = 30 (많이 흐림)
        self.blur_slider.setValue(5)     # 처음 시작 위치 = 5

        # ── 버튼과 기능 연결 ──────────────────────
        # .clicked.connect(함수) = "이 버튼 누르면 이 함수 실행해!"
        self.normal_btn.clicked.connect(self.show_normal)
        self.gray_btn.clicked.connect(self.show_gray)
        self.blur_btn.clicked.connect(self.show_blur)
        self.edge_btn.clicked.connect(self.show_edge)
        self.bright_btn.clicked.connect(self.show_bright)
        self.dark_btn.clicked.connect(self.show_dark)
        self.flip_btn.clicked.connect(self.show_flip)
        self.sepia_btn.clicked.connect(self.show_sepia)
        self.rotate_btn.clicked.connect(self.show_rotate)
        self.face_btn.clicked.connect(self.show_face_detect)
        self.equalize_btn.clicked.connect(self.show_equalize)
        self.open_btn.clicked.connect(self.open_image)
        self.save_btn.clicked.connect(self.save_image)
        # 슬라이더는 값이 바뀔 때마다 블러 다시 적용
        self.blur_slider.valueChanged.connect(self.show_blur)

        # ── 화면 배치(레이아웃) ───────────────────
        # HBoxLayout = 가로로 나란히 놓는 줄
        # 버튼이 많으니까 4줄로 나눠서 배치해요

        btn_row1 = QHBoxLayout()  # 1줄: 기본 필터
        for btn in [self.normal_btn, self.gray_btn, self.blur_btn, self.edge_btn]:
            btn_row1.addWidget(btn)

        btn_row2 = QHBoxLayout()  # 2줄: 색감 필터
        for btn in [self.bright_btn, self.dark_btn, self.flip_btn, self.sepia_btn]:
            btn_row2.addWidget(btn)

        btn_row3 = QHBoxLayout()  # 3줄: 변형/인식
        for btn in [self.rotate_btn, self.face_btn, self.equalize_btn]:
            btn_row3.addWidget(btn)

        btn_row4 = QHBoxLayout()  # 4줄: 파일 관리
        for btn in [self.open_btn, self.save_btn]:
            btn_row4.addWidget(btn)

        # VBoxLayout = 위에서 아래로 쌓는 큰 틀
        layout = QVBoxLayout()
        layout.addWidget(self.image_label)      # 1. 사진 액자
        layout.addWidget(QLabel("블러 강도:"))  # 2. 슬라이더 설명 글자
        layout.addWidget(self.blur_slider)       # 3. 슬라이더
        layout.addLayout(btn_row1)               # 4. 버튼 1줄
        layout.addLayout(btn_row2)               # 5. 버튼 2줄
        layout.addLayout(btn_row3)               # 6. 버튼 3줄
        layout.addLayout(btn_row4)               # 7. 버튼 4줄
        self.setLayout(layout)  # 이 배치를 창에 최종 적용

        self.update_image()  # 앱 시작하자마자 사진 화면에 표시


    # =============================================
    # update_image = 사진을 화면 액자에 끼워 넣는 함수
    # OpenCV 사진(BGR) → PyQt5가 이해하는 형식으로 변환 필요
    # =============================================
    def update_image(self):
        img = self.current

        if len(img.shape) == 2:
            # shape가 (높이, 너비) 2개 → 흑백 사진
            # 흑백은 픽셀마다 숫자 1개 (밝기만 있음)
            h, w = img.shape
            qt_image = QImage(
                img.data,              # 픽셀 데이터
                w, h,                  # 가로, 세로 크기
                w,                     # 한 줄의 바이트 수 (흑백은 픽셀=1바이트)
                QImage.Format_Grayscale8  # "이건 흑백이야" 형식 지정
            )
        else:
            # shape가 (높이, 너비, 3) 3개 → 컬러 사진
            # OpenCV는 BGR 순서, PyQt5는 RGB 순서 → 변환 필수!
            rgb = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
            h, w, ch = rgb.shape  # ch = 채널 수 (컬러면 항상 3)
            qt_image = QImage(
                rgb.data,
                w, h,
                ch * w,               # 한 줄의 바이트 수 (컬러는 픽셀당 3바이트)
                QImage.Format_RGB888  # "이건 컬러야" 형식 지정
            )

        # QImage → QPixmap으로 바꿔야 QLabel에 표시 가능
        pixmap = QPixmap.fromImage(qt_image)
        self.image_label.setPixmap(pixmap)  # 액자에 사진 끼우기


    # =============================================
    # 아래부터는 버튼마다 연결된 필터 함수들
    # 공통 흐름: 원본에 필터 적용 → self.current에 저장 → 화면 갱신
    # =============================================

    def show_normal(self):
        # 원본 그대로 복사해서 보여주기 (필터 초기화)
        self.current = self.original.copy()
        self.update_image()

    def show_gray(self):
        # BGR 컬러 → GRAY 흑백으로 변환
        # 각 픽셀의 (B,G,R) 3개 숫자를 밝기 1개 숫자로 합침
        self.current = cv2.cvtColor(self.original, cv2.COLOR_BGR2GRAY)
        self.update_image()

    def show_blur(self):
        # 슬라이더 값으로 흐림 정도 결정
        val = self.blur_slider.value()
        ksize = val * 2 + 1  # GaussianBlur는 홀수만 받아서 계산 (1→3, 2→5, 3→7...)

        # GaussianBlur = 주변 픽셀 색을 평균내서 경계를 뭉개는 원리
        # (ksize, ksize) = 몇 칸 주변까지 볼지 (클수록 더 뿌예짐)
        self.current = cv2.GaussianBlur(self.original, (ksize, ksize), 0)
        self.update_image()

    def show_edge(self):
        # Canny = 색이 급격히 바뀌는 경계선만 흰색으로 추출
        # 100, 200 = 경계로 인정하는 민감도 (낮을수록 선이 많아짐)
        self.current = cv2.Canny(self.original, 100, 200)
        self.update_image()

    def show_bright(self):
        # convertScaleAbs = 픽셀값을 수식으로 조절
        # 새 픽셀값 = alpha(대비) × 원본값 + beta(밝기)
        # beta=+80 → 모든 픽셀에 80 더하기 → 전체 밝아짐
        self.current = cv2.convertScaleAbs(self.original, alpha=1.0, beta=80)
        self.update_image()

    def show_dark(self):
        # beta=-80 → 모든 픽셀에서 80 빼기 → 전체 어두워짐
        self.current = cv2.convertScaleAbs(self.original, alpha=1.0, beta=-80)
        self.update_image()

    def show_flip(self):
        # flip(이미지, 방향)
        # 1 = 좌우 반전 (거울), 0 = 상하 반전, -1 = 둘 다
        self.current = cv2.flip(self.original, 1)
        self.update_image()

    def show_sepia(self):
        # 세피아 = 색을 갈색 계열로 바꾸는 옛날 사진 효과
        # kernel = 각 픽셀의 BGR 값을 섞는 비율표 (3×3 행렬)
        # 예: 새 파랑 = 원본파랑×0.131 + 원본초록×0.534 + 원본빨강×0.272
        kernel = np.array([[0.272, 0.534, 0.131],
                           [0.349, 0.686, 0.168],
                           [0.393, 0.769, 0.189]])
        sepia = cv2.transform(self.original, kernel)
        # clip = 계산 결과가 0~255 범위를 벗어나지 않게 잘라냄
        self.current = np.clip(sepia, 0, 255).astype(np.uint8)
        self.update_image()

    def show_rotate(self):
        h, w = self.original.shape[:2]
        # getRotationMatrix2D(중심점, 각도, 크기배율)
        # 사진 중심을 기준으로 90도 회전하는 변환 행렬 계산
        matrix = cv2.getRotationMatrix2D((w // 2, h // 2), 90, 1.0)
        # warpAffine = 위에서 계산한 행렬대로 실제로 사진을 변형
        self.current = cv2.warpAffine(self.original, matrix, (w, h))
        self.update_image()

    def show_face_detect(self):
        # OpenCV에 내장된 얼굴 인식 모델 파일 불러오기
        # (미리 수천 장 얼굴 사진으로 학습된 모델)
        cascade_path = cv2.data.haarcascades + 'haarcascade_frontalface_default.xml'
        face_cascade = cv2.CascadeClassifier(cascade_path)

        # 얼굴 인식은 흑백으로 하는 게 더 정확함
        gray = cv2.cvtColor(self.original, cv2.COLOR_BGR2GRAY)

        # detectMultiScale = 사진을 여러 크기로 스캔해서 얼굴 위치 찾기
        # 1.1 = 스캔 크기 변화 비율, 4 = 얼굴로 인정하는 최소 횟수
        faces = face_cascade.detectMultiScale(gray, 1.1, 4)

        result = self.original.copy()
        for (x, y, w, h) in faces:
            # 찾은 얼굴 위치마다 초록색 사각형 그리기
            # (x,y) = 왼쪽 위 꼭짓점, (x+w, y+h) = 오른쪽 아래 꼭짓점
            cv2.rectangle(result, (x, y), (x + w, y + h), (0, 255, 0), 3)
        self.current = result
        self.update_image()

    def show_equalize(self):
        # 히스토그램 평탄화 = 어두운 사진의 밝기 분포를 고르게 펴줌
        # YUV 형식으로 변환 (Y=밝기, U/V=색상 정보 분리)
        img_yuv = cv2.cvtColor(self.original, cv2.COLOR_BGR2YUV)
        # 밝기(Y) 채널에만 equalizeHist 적용 (색은 건드리지 않음)
        img_yuv[:, :, 0] = cv2.equalizeHist(img_yuv[:, :, 0])
        # 다시 BGR로 변환해서 저장
        self.current = cv2.cvtColor(img_yuv, cv2.COLOR_YUV2BGR)
        self.update_image()

    def open_image(self):
        # 파일 탐색기 팝업 열기
        # "Images (*.png *.jpg *.bmp)" = 이 확장자 파일만 보이게 필터
        path, _ = QFileDialog.getOpenFileName(
            self, "이미지 열기", "", "Images (*.png *.jpg *.bmp)"
        )
        if path:  # 파일을 실제로 골랐을 때만 실행 (취소하면 path = "")
            self.original = cv2.imread(path)    # 새 원본으로 교체
            self.current  = self.original.copy()
            self.update_image()

    def save_image(self):
        # 저장할 파일 이름/위치 고르는 팝업 열기
        path, _ = QFileDialog.getSaveFileName(
            self, "저장", "", "Images (*.png *.jpg)"
        )
        if path:  # 경로를 실제로 입력했을 때만 저장
            # imwrite = 현재 화면의 사진을 파일로 저장
            cv2.imwrite(path, self.current)


# =============================================
# 앱 실행 시작점
# =============================================
app = QApplication(sys.argv)  # PyQt5 앱 엔진 시작

window = PhotoFilter()  # 우리가 만든 창 생성
window.show()           # 창 화면에 표시

# exec_() = 창을 닫을 때까지 앱을 계속 실행
# sys.exit() = 창 닫으면 프로그램도 완전히 종료
sys.exit(app.exec_())