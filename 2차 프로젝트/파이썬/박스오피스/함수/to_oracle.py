import cx_Oracle
def preprocessed_df_to_oracle(df):
    con = cx_Oracle.connect('mbti/1111@192.168.30.28:1521/xe')
    cur = con.cursor()
    sql_delete = '''
                    DELETE FROM BOX_MOVIE
                    '''
    cur.execute(sql_delete)
    print('전날 데이터 삭제 완료')
    sql_insert = '''
                    insert into BOX_MOVIE(당일영화순위, 전일대비순위증감분, 랭킹신규진입여부, 영화대표코드, 국문영화명, 영화개봉일, 당일관객수,전일대비관객수증감,누적관객수) 
                    values(:rank, :rankInten, :rankOldAndNew, :movieCd, :movieNm, :openDt, :audiCnt,:audiInten,:audiAcc)
                    '''
    for n in range(0, len(df)):
        rank = int(df.iloc[n]['rank'])
        rankInten = int(df.iloc[n]['rankInten'])  # int 값에 대해서는 int 형으로 변환해줘야 한다.
        # movieNmEn = df.iloc[0]['movieNmEn']
        rankOldAndNew = df.iloc[n]['rankOldAndNew']  # 현재 데이터 프레임의 행인덱스가 date_time이므로 loc가 안된다.
        movieCd = df.iloc[n]['movieCd']
        movieNm = df.iloc[n]['movieNm']
        openDt = df.iloc[n]['openDt']
        audiCnt = int(df.iloc[n]['audiCnt'])
        audiInten = int(df.iloc[n]['audiInten'])
        audiAcc = int(df.iloc[n]['audiAcc'])
        # directors = df.iloc[0]['directors']
        # companys = df.iloc[0]['companys']

        cur.execute(sql_insert,
                    (rank, rankInten, rankOldAndNew, movieCd, movieNm,
                     openDt, audiCnt, audiInten, audiAcc)
                    )

    con.commit()
    cur.close()
    con.close()
    print('오라클 저장 완료')

def preprocessed_df_to_oracle_ver_rank(df, yyyymmdd):
    con = cx_Oracle.connect('mbti/1111@192.168.30.28:1521/xe')
    cur = con.cursor()
    sql_insert = '''
                       insert into BOX_RANK_DATA(RANK_DATE, MOVIENM, RANK) 
                       values(:yyyymmdd, :movieNm, :rank)
                       '''
    print("sql_insert 이상무")
    for n in range(0, len(df)):
        movieNm = df.iloc[n]['movieNm']
        rank = int(df.iloc[n]['rank'])
        print("암튼 여기도 이상무")

        cur.execute(sql_insert,
                    (yyyymmdd, movieNm, rank)
                    )
        print("execute 이상무")

    con.commit()
    cur.close()
    con.close()
    print('오라클 저장 완료')