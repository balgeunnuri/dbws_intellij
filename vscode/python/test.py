print('wow!!!')

x = 10;
name = 'mz';

print(x)
print(name)

a = 5;
b = 3.14;
c = True;

print(type(a))
print(type(b))
print(type(c))

print(3 < x < 5)
print(3 < x or x < 5)
print(not x > 3) # !(x>3)

age = 15;
if age >= 18:
    print('성인입니다.') # 들여쓰기 중요. 범위 구분(약속)
elif age >= 13:
    print('청소년')
else:
    print('어린이')


for i in range(3):
    print(i)
for i in range(1,5,2): # 1부터 시작, 5까지, 증감식 i+=2
    print(i)


print("--------")

num = 3;
while num > 0:
    print(num)
    num -= 1; # ++,-- 없음

# 함수 정의 디파인 약자 def
def sayHi(param):
    print(param + '아 안녕?')
    print(f"{param}아 안녕~~?") #프린트 f랑 같은 의미 포맷팅 f, 백틱

sayHi('경민') # 범위 이탈시켜줘야함. 들여쓰기 하면 안됨!

# 리스트
fruits = ["apple", "banana", "mango"]
print(fruits[1])

person = {"name" : "benr", "age" : 20}
print(person["name"])

import math #import는 위에 적는 게 관례.!
print(math.ceil(1.3))
print(math.sqrt(16))

# 쓰기
with open("C:\\benr_new\\vscode\\python\\pytest.txt", "w") as f:
    f.write("asdasd");

# 읽기
with open("C:\\benr_new\\vscode\\python\\pytest.txt", "r") as f:
    print(f.read())


try:
    result = 10 / 0
except ZeroDivisionError:
    print("/ 0 ㄴㄴ")


# 튜플(불변) 리스트
my_tuple = (1,2,3)
print(my_tuple)
print(my_tuple[1]) #리스트니까 []사용하는 건 맞는데, ..?

# 길이
print(len(my_tuple))

t1 = 1,2
t2 = 3,4
t3 = t1 + t2
print(t1)
print(t2)
print(t3[2])

# 하나만 쓸 때는 (,)필수 
t4 = (1,)
print(type(t4))

def calc(a,b):
    return a+b, a*b #리턴값 여러개 가능. 그런데 tuple에 담아줌. 바꾸려면 리스트로 바꿔쓸 생각하기

result = calc(1,5)
print(result)

hap, gob = result
print(hap)
print(gob)

print('----------------')
_, one = result
print(one)

# 튜플 유즈케이스 더 알아보고 공부하기
# 장고 프레임워크(관리자모드 도와줌, 규모가 있어야 좋음) == 자바 스프링 부트
# 채팅앱 => top down 방식/ open cv