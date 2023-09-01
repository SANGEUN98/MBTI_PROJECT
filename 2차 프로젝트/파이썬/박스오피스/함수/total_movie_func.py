from api_parsing import get_request_url, abc_cut, get_rank_request_url, abc_rank_cut
import json
import pandas as pd
from datetime import datetime, timedelta
from to_oracle import preprocessed_df_to_oracle, preprocessed_df_to_oracle_ver_rank
from another_info import get_img_url_and_movie_info, booking_link
import cx_Oracle
def movie_info_collector():
    url = 'http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=ba93000485f503f718ba1b8973bbf818'
    request_time = get_update_time_info()
    yyyymmdd = request_time.strftime("%Y%m%d")
    hhmm = request_time.strftime("%H%M")

    raw_str_json = get_request_url(url, yyyymmdd, hhmm)

    if raw_str_json:
        raw_json = json.loads(raw_str_json)
    data2 = []
    data2 = abc_cut(raw_json)

    column_list = ["rank", "rankInten", "rankOldAndNew", "movieCd", "movieNm", "openDt", "audiCnt", "audiInten",
                   "audiAcc"]

    df = pd.DataFrame(data2[0])
    df3 = pd.DataFrame(data2)
    print(df3)

    num = 0
    for l in column_list:
        # print(l)
        df3.rename(columns={num: l}, inplace=True)
        num += 1
    print(df3)
    preprocessed_df_to_oracle(df3)
    get_img_url_and_movie_info()
    booking_link()

def get_update_time_info():
    now = datetime.now()

    if now.hour > 00:
        print(f"어제의 박스오피스 데이터를 업데이트 합니다. 오늘의 날짜: {now}")
        return now - timedelta(days=1)

def movie_rank_collector():
    con = cx_Oracle.connect('mbti/1111@192.168.30.28:1521/xe')
    cur = con.cursor()
    sql_delete = '''
                          DELETE FROM BOX_RANK_DATA
                          '''
    cur.execute(sql_delete)
    con.commit()
    cur.close()
    print('전날 데이터 삭제 완료')
    for rank_date in range(7):
        url = 'http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=ba93000485f503f718ba1b8973bbf818'
        request_rank_time = get_rank_update_time_info(rank_date)
        yyyymmdd = request_rank_time.strftime("%Y%m%d")
        print("movie_rank 이상무")
        hhmm = request_rank_time.strftime("%H%M")

        raw_str_json = get_rank_request_url(url, yyyymmdd, hhmm)
        print("raw_str 이상무")

        if raw_str_json:
            raw_json = json.loads(raw_str_json)
        data2 = []
        data2 = abc_rank_cut(raw_json)

        column_list = ["rank", "movieNm"]

        df = pd.DataFrame(data2[0])
        df3 = pd.DataFrame(data2)
        print(df3)

        num = 0
        for l in column_list:
            # print(l)
            df3.rename(columns={num: l}, inplace=True)
            num += 1
        print(df3)
        preprocessed_df_to_oracle_ver_rank(df3, yyyymmdd)

def get_rank_update_time_info(rank_date):
    now = datetime.now()

    if now.hour > 00:
        print(f"어제의 박스오피스 데이터를 업데이트 합니다. 오늘의 날짜: {now}")
        return now - timedelta(days=(1+rank_date))
