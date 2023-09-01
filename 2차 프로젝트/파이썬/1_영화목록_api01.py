## 목적: 수동으로 OpenAPI 호출하기
# OpenAPI 호출/응답 이해하기

# base URL
# http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst

# OpenAPI 호출 방식
# baseURL?파라메터1=값&파라메터2=값....

#http://apis.data.go.kr/B551182/pubReliefHospService/getpubReliefHospList?ServiceKey=bgOnt78reFNsTUJuAwlI30JDObTxX6hbJCxyApJCtuf3xjJZJ/mOs8Vhg3GZAsLc1fXTkQ9sjq0mTEupWDdyyA==&numOfRows=10&pageNo=1
# 부평동 격자 x, 격자 y 정보
nx = 55
ny = 125
# 00분~40분: 정보 업데이터 않함
# 40분~00분: 정보 업데이트 함
# 현재시간으로 테스트
# XML 타입
#http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?ServiceKey=bgOnt78reFNsTUJuAwlI30JDObTxX6hbJCxyApJCtuf3xjJZJ/mOs8Vhg3GZAsLc1fXTkQ9sjq0mTEupWDdyyA==&base_date=20230706&base_time=1400&nx=55&ny=125
# JSON 타입
#http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?ServiceKey=HVgh0GJtfha0bssIG8/oBb7dkuTWWgOnt3o47r4Wa1/SrD6VRDqJ0cOzT/6T4vL3KX4JV0bKzNZl9WqYpOdLJg==/mOs8Vhg3GZAsLc1fXTkQ9sjq0mTEupWDdyyA==&base_date=20230706&base_time=1400&nx=55&ny=125&dataType=JSON

# 대용량데이터 수신시: 현재 OpenAPI는 최대 8개의 데이터를 수집하기 때문에 특별히 설정할 필요가 없으나
# numOfRows/pageNo을 이해하기 위해 실습한다.
#http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?ServiceKey=bgOnt78reFNsTUJuAwlI30JDObTxX6hbJCxyApJCtuf3xjJZJ/mOs8Vhg3GZAsLc1fXTkQ9sjq0mTEupWDdyyA==&base_date=20230706&base_time=1400&nx=55&ny=125&dataType=JSON&numOfRows=10&pageNo=1

# 8개 중에 첫번째 페이지 4개
#http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?ServiceKey=bgOnt78reFNsTUJuAwlI30JDObTxX6hbJCxyApJCtuf3xjJZJ/mOs8Vhg3GZAsLc1fXTkQ9sjq0mTEupWDdyyA==&base_date=20230706&base_time=1400&nx=55&ny=125&dataType=JSON&numOfRows=4&pageNo=1
#http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?ServiceKey=bgOnt78reFNsTUJuAwlI30JDObTxX6hbJCxyApJCtuf3xjJZJ/mOs8Vhg3GZAsLc1fXTkQ9sjq0mTEupWDdyyA==&base_date=20230706&base_time=1400&nx=55&ny=125&dataType=JSON&numOfRows=4&pageNo=2

# HVgh0GJtfha0bssIG8%2FoBb7dkuTWWgOnt3o47r4Wa1%2FSrD6VRDqJ0cOzT%2F6T4vL3KX4JV0bKzNZl9WqYpOdLJg%3D%3D

# HVgh0GJtfha0bssIG8/oBb7dkuTWWgOnt3o47r4Wa1/SrD6VRDqJ0cOzT/6T4vL3KX4JV0bKzNZl9WqYpOdLJg==
