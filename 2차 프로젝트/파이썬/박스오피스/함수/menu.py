import time
import threading
from total_movie_func import movie_rank_collector, movie_info_collector

def menu_start():
    def movie_info_scheduler():
        print('영화 일일 박스오피스 데이터 수집기 스케줄러 동작.\n')
        while True:
            print("일일 박스오피스 데이터 수집시작.")
            movie_info_collector()
            print("일일 박스오피스 데이터 수집완료.")
            print("일일 박스오피스 랭크 데이터 수집시작.")
            movie_rank_collector()
            print("일일 박스오피스 랭크 데이터 수집완료.")
            time.sleep(86400)  # 24시간 주기로 데이터 수집


    def print_main_menu():
        print('\n1. 박스오피스 데이터 실시간 데이터 구축')
        print('2. 스케줄러 종료')
        print('* 엔터: 메뉴 업데이트\n')


    while True:
        print_main_menu()
        print('아래행에 메뉴입력: ')
        selection = input()
        if selection == '':
            continue
        else:
            menu_num = int(selection)

        if (menu_num == 1):
            t = threading.Thread(target=movie_info_scheduler, daemon=True)
            t.start()
        elif (menu_num == 2):
            break
        elif (menu_num == 0):
            continue

menu_start()