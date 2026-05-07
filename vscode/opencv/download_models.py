import urllib.request
import os

files = {
    # 이미 받은 것들 (건너뜀)
    "opencv_face_detector.pbtxt": "https://raw.githubusercontent.com/spmallick/learnopencv/master/AgeGender/opencv_face_detector.pbtxt",
    "opencv_face_detector_uint8.pb": "https://github.com/spmallick/learnopencv/raw/master/AgeGender/opencv_face_detector_uint8.pb",
    "age_deploy.prototxt": "https://raw.githubusercontent.com/spmallick/learnopencv/master/AgeGender/age_deploy.prototxt",
    "gender_deploy.prototxt": "https://raw.githubusercontent.com/spmallick/learnopencv/master/AgeGender/gender_deploy.prototxt",

    # ✅ caffemodel 두 개만 HuggingFace로 변경
    "age_net.caffemodel": "https://huggingface.co/AjaySharma/genderDetection/resolve/5cde30ccaa3ebff2e3d06876bf6412f19be183c3/age_net.caffemodel",
    "gender_net.caffemodel": "https://huggingface.co/AjaySharma/genderDetection/resolve/5cde30ccaa3ebff2e3d06876bf6412f19be183c3/gender_net.caffemodel",
}

for filename, url in files.items():
    if os.path.exists(filename):
        print(f"이미 있음: {filename}")
        continue
    print(f"다운로드 중: {filename} ...")
    urllib.request.urlretrieve(url, filename)
    print(f"완료: {filename} ✅")

print("\n전부 완료!")