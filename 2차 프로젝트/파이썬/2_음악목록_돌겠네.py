# 목적: 파이썬 코드를 활용하여 OpenAPI 호출 자동화
# Step1: OpenAPI를 제공하는 사이트에서 제공하는 샘플 프로그램을 확보한다.

import requests
# Encoding Key
# access_key = 'bgOnt78reFNsTUJuAwlI30JDObTxX6hbJCxyApJCtuf3xjJZJ%2FmOs8Vhg3GZAsLc1fXTkQ9sjq0mTEupWDdyyA%3D%3D'
# Decoding key
access_key ='AIzaSyBXMz6OWGKGffzDMZEgnaX-vyiSXRuO4pI'

def get_request_url():
    url = 'https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=30&q=INFJ%ED%94%8C%EB%A0%88%EC%9D%B4%EB%A6%AC%EC%8A%A4%ED%8A%B8&type=video&key=AIzaSyBXMz6OWGKGffzDMZEgnaX-vyiSXRuO4pI'
    params = {'key': access_key, 'part': 'snippet', 'maxResults': 30,
              'dataType': 'JSON', 'q': 'INFJ플레이리스트', 'type': 'video'
              }
    response = requests.get(url, params=params)
    # response.content # => response.content는 한글이 인코딩된 형식이므로
    #                       response.text 를 응답받기로함
    return response.text

raw_json = get_request_url()
print(raw_json)