export function MovieForm(lists, first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth) {
    console.log('실행됨')
    // console.log(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth)

    lists.sort((a,b) => a.rank - b.rank)
    first.sort((a,b) => a.rankDate - b.rankDate)
    second.sort((a,b) => a.rankDate - b.rankDate)
    third.sort((a,b) => a.rankDate - b.rankDate)
    fourth.sort((a,b) => a.rankDate - b.rankDate)
    fifth.sort((a,b) => a.rankDate - b.rankDate)
    sixth.sort((a,b) => a.rankDate - b.rankDate)
    seventh.sort((a,b) => a.rankDate - b.rankDate)
    eighth.sort((a,b) => a.rankDate - b.rankDate)
    ninth.sort((a,b) => a.rankDate - b.rankDate)
    tenth.sort((a,b) => a.rankDate - b.rankDate)
    const form1 = document.getElementById('form1')
    const form2 = document.getElementById('form2')
    let abc = []
    let list = []
    let firsts = []
    let seconds = []
    let thirds = []
    let fourths = []
    let fifths = []
    let sixths = []
    let sevenths = []
    let eighths = []
    let ninths = []
    let tenths = []
    let rankList = [firsts, seconds, thirds, fourths, fifths, sixths, sevenths, eighths, ninths, tenths]
    for (let i = 0; i < lists.length; i++) {
        let abc = []
        abc.push(lists[i].boxURL)
        abc.push(lists[i].movieNm)
        abc.push(lists[i].openDt)
        abc.push(lists[i].rank)
        abc.push(lists[i].boxInfo)
        abc.push(lists[i].bookingURL)
        list.push(abc)
    }

    for (let i = 0; i < first.length; i++) {
        let abc = []
        abc.push(first[i].rank)
        abc.push(first[i].movieNm)
        abc.push(first[i].rankDate)
        firsts.push(abc)
    }
    for (let i = 0; i < second.length; i++) {
        let abc = []
        abc.push(second[i].rank)
        abc.push(second[i].movieNm)
        abc.push(second[i].rankDate)
        seconds.push(abc)
    }
    for (let i = 0; i < third.length; i++) {
        let abc = []
        abc.push(third[i].rank)
        abc.push(third[i].movieNm)
        abc.push(third[i].rankDate)
        thirds.push(abc)
    }
    for (let i = 0; i < fourth.length; i++) {
        let abc = []
        abc.push(fourth[i].rank)
        abc.push(fourth[i].movieNm)
        abc.push(fourth[i].rankDate)
        fourths.push(abc)
    }
    for (let i = 0; i < fifth.length; i++) {
        let abc = []
        abc.push(fifth[i].rank)
        abc.push(fifth[i].movieNm)
        abc.push(fifth[i].rankDate)
        fifths.push(abc)
    }
    for (let i = 0; i < sixth.length; i++) {
        let abc = []
        abc.push(sixth[i].rank)
        abc.push(sixth[i].movieNm)
        abc.push(sixth[i].rankDate)
        sixths.push(abc)
    }
    for (let i = 0; i < seventh.length; i++) {
        let abc = []
        abc.push(seventh[i].rank)
        abc.push(seventh[i].movieNm)
        abc.push(seventh[i].rankDate)
        sevenths.push(abc)
    }
    for (let i = 0; i < eighth.length; i++) {
        let abc = []
        abc.push(eighth[i].rank)
        abc.push(eighth[i].movieNm)
        abc.push(eighth[i].rankDate)
        eighths.push(abc)
    }
    for (let i = 0; i < ninth.length; i++) {
        let abc = []
        abc.push(ninth[i].rank)
        abc.push(ninth[i].movieNm)
        abc.push(ninth[i].rankDate)
        ninths.push(abc)
    }
    for (let i = 0; i < tenth.length; i++) {
        let abc = []
        abc.push(tenth[i].rank)
        abc.push(tenth[i].movieNm)
        abc.push(tenth[i].rankDate)
        tenths.push(abc)
    }

    for (let i = 0; i < list.length; i++) {
        const li = document.createElement('li')
        const div1 = document.createElement('div')
        const div2 = document.createElement('div')
        const div3 = document.createElement('div')
        const div4 = document.createElement('div')
        const div5 = document.createElement('div')
        const img1 = document.createElement('img')
        const button1 = document.createElement('button')
        const button2 = document.createElement('button')
        const input= document.createElement('input')
        // const a = document.createElement('a')

        li.setAttribute('id', `리스트${i}`)
        form1.appendChild(li)
        div1.setAttribute('name', '이미지')
        li.appendChild(div1)
        img1.setAttribute('src', `${list[i][0]}`)
        div1.appendChild(img1)
        div2.setAttribute('name', '영화제목')
        div2.textContent = `${list[i][1]}`
        li.appendChild(div2)
        div3.setAttribute('name', '개봉일')
        div3.textContent = `${list[i][2]}`
        li.appendChild(div3)
        div4.setAttribute('name', '영화순위')
        div4.textContent = `${list[i][3]}위`
        li.appendChild(div4)
        div5.setAttribute('name', 'button')
        button1.setAttribute('name', 'button1')
        button1.addEventListener('click', function() {
            window.open(`${list[i][5]}`, '_blank');
        });
        button1.textContent = '예매하기'
        button2.setAttribute('name', 'button2')
        button2.textContent = '정보'
        li.appendChild(div5)
        div5.appendChild(button1)
        div5.appendChild(button2)
        input.setAttribute('type', 'checkbox')
        div5.appendChild(input)

        const div6 = document.createElement('div')
        const div7 = document.createElement('div')
        const div8 = document.createElement('div')
        const div9 = document.createElement('div')
        const div10 = document.createElement('div')
        const div11 = document.createElement('div')
        const canvas = document.createElement('canvas')
        const img2 = document.createElement('img')
        const p = document.createElement('p')
        const p1 = document.createElement('p')
        div6.setAttribute('class', 'modalWrap')
        div6.setAttribute('id', `modalWrap${i}`)
        form2.appendChild(div6)
        div7.setAttribute('class', 'modalBody')
        div6.appendChild(div7)
        div7.appendChild(div8)

        // jQuery 코드
        $(document).ready(function() {
            $('.circle').on('click', function(event) {
                event.preventDefault(event);  // 버튼 클릭 시 기본 동작(새로고침) 막기
                // 버튼을 눌렀을 때 수행할 동작 추가
                // 예를 들어, 모달을 닫도록 하는 코드 등을 여기에 추가
                const modalId = this.getAttribute('modal0'); // 모달 ID 가져오기
                const modal = document.getElementById(modal0);event.preventDefault();  // 버튼 클릭 시 기본 동작(새로고침) 막기
                modal0[i].style.display = 'none'; // 모달 숨기기
                var animClass = $(this).data('animation');
                var removeTime = $(this).data('remove');

                if ($(this).hasClass(animClass)) {
                    $(this).removeClass(animClass);
                } else {
                    $(this).addClass(animClass);

                    if (typeof removeTime !== 'undefined') {
                        var el = $(this);
                        setTimeout(function() {
                            el.removeClass(animClass);
                        }, removeTime);
                    }
                }
            });
        });

// 부모 컨테이너 엘리먼트 생성
        const circCont = document.createElement('div');
        circCont.classList.add('circCont');

// 버튼 엘리먼트 생성
        const button = document.createElement('button');
        button.classList.add('circle','custom-class');
        button.setAttribute('data-animation', 'simpleRotate');
        button.setAttribute('data-remove', '200');

// 버튼 클릭 시 동작 추가
        button.addEventListener('click', function() {
            const animClass = this.getAttribute('data-animation');
            const removeTime = parseInt(this.getAttribute('data-remove'));

            if (this.classList.contains(animClass)) {
                this.classList.remove(animClass);
            } else {
                this.classList.add(animClass);

                if (!isNaN(removeTime)) {
                    const el = this;
                    setTimeout(function() {
                        el.classList.remove(animClass);
                    }, removeTime);
                }
            }
        });


// 요소들을 부모 컨테이너에 추가
        circCont.appendChild(button);

// 부모 컨테이너를 적절한 위치에 추가 (예: body 요소)
        div8.appendChild(circCont);

        // 부모 컨테이너 엘리먼트 생성
        const container = document.createElement('div');
        container.setAttribute('name','container')

        div9.setAttribute('name', 'popup-img')
        container.appendChild(div9)
        img2.setAttribute('src', `${list[i][0]}`)
        img2.setAttribute('name','form2Image')
        img2.setAttribute('class','form2Image')
        div9.appendChild(img2)
        div10.setAttribute('name', '줄거리')
        container.appendChild(div10)
        p.textContent = `${list[i][4]}`
        p.setAttribute('name','inform')
        div10.appendChild(p)

        div7.appendChild(container)
        // 차트 존
        div11.setAttribute('name', 'chart')
        div7.appendChild(div11)
        p1.setAttribute("id", "nowRank")
        p1.textContent = `현재 ${rankList[i][0][0]} 위`
        div11.appendChild(p1)
        canvas.setAttribute('name','canvas')
        canvas.setAttribute('id',`canvas${i}`)
        div11.appendChild(canvas)
    }


    const modal0 = document.getElementsByClassName('modalWrap')
    const btn = document.querySelectorAll('#form1 button')
    const num = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]
    // console.log(first[first.length - 1].rankDate)
    for (let i = 0; i < 10; i++) {


        // let rankChart = document.getElementById(`canvas${i}`).getContext('2d');
        function PopUp(e) {
            e.preventDefault();
            modal0[i].style.display = 'block';
            let rankChart = document.getElementById(`canvas${i}`).getContext('2d');

// 스케줄러 작성 전까지는 최신 데이터 사용
// =======================================================================================
            // 현재 날짜 구하는 객체 설정
            // var today = new Date();
            // 7일 전 날짜부터 1일 전 날짜까지의 날짜 배열 생성
            // var dateArray = Array.from({ length: 7 }, (_, index) => {
            //     var daysAgo = today.getTime() - (index + 1) * 24 * 60 * 60 * 1000;
            //     var date = new Date(daysAgo);
            //     var year = date.getFullYear();
            //     var month = String(date.getMonth() + 1).padStart(2, "0");
            //     var day = String(date.getDate()).padStart(2, "0");
            //     return year + month + day;
            // });
// =======================================================================================   
            let date = Number(first[first.length - 1].rankDate);
            let dateArray=[]
            for (let i=0; i< 7; i++) {
                dateArray.push(date - i)
            }


            let sevenDaysAgo = 11; // 기본값으로 초기화
            let sixDaysAgo = 11; // 기본값으로 초기화
            let fiveDaysAgo = 11; // 기본값으로 초기화
            let fourDaysAgo = 11; // 기본값으로 초기화
            let threeDaysAgo = 11; // 기본값으로 초기화
            let twoDaysAgo = 11; // 기본값으로 초기화
            let yesterday = 11; // 기본값으로 초기화

            // for (let x = 0; x < `${rankList[i].length}`; x++) {
            //     if (dateArray[6] === `${rankList[i][x][2]}`) {
            //         sevenDaysAgo = `${rankList[i][x][0]}`;
            //         break; // 조건을 만족한 경우 반복 종료
            //     }
            // }

            // for (let x = 0; x < `${rankList[i].length}`; x++) {
            //     if (dateArray[5] === `${rankList[i][x][2]}`) {
            //         sixDaysAgo = `${rankList[i][x][0]}`;
            //         break; // 조건을 만족한 경우 반복 종료
            //     }
            // }

            // for (let x = 0; x < `${rankList[i].length}`; x++) {
            //     if (dateArray[4] === `${rankList[i][x][2]}`) {
            //         fiveDaysAgo = `${rankList[i][x][0]}`;
            //         break; // 조건을 만족한 경우 반복 종료
            //     }
            // }

            // for (let x = 0; x < `${rankList[i].length}`; x++) {
            //     if (dateArray[3] === `${rankList[i][x][2]}`) {
            //         fourDaysAgo = `${rankList[i][x][0]}`;
            //         break; // 조건을 만족한 경우 반복 종료
            //     }
            // }

            // for (let x = 0; x < `${rankList[i].length}`; x++) {
            //     if (dateArray[2] === `${rankList[i][x][2]}`) {
            //         threeDaysAgo = `${rankList[i][x][0]}`;
            //         break; // 조건을 만족한 경우 반복 종료
            //     }
            // }

            // for (let x = 0; x < `${rankList[i].length}`; x++) {
            //     if (dateArray[1] === `${rankList[i][x][2]}`) {
            //         twoDaysAgo = `${rankList[i][x][0]}`;
            //         break; // 조건을 만족한 경우 반복 종료
            //     }
            // }

            // for (let x = 0; x < `${rankList[i].length}`; x++) {
            //     if (dateArray[0] === `${rankList[i][x][2]}`) {
            //         yesterday = `${rankList[i][x][0]}`;
            //         break; // 조건을 만족한 경우 반복 종료
            //     }
            // }

            for (let x = 0; x < rankList[i].length; x++) {
                if (dateArray[6] === Number(rankList[i][x][2])) {
                    sevenDaysAgo = rankList[i][x][0];
                    break; // 조건을 만족한 경우 반복 종료
                }
            }

            for (let x = 0; x < rankList[i].length; x++) {
                if (dateArray[5] === Number(rankList[i][x][2])) {
                    sixDaysAgo = rankList[i][x][0];
                    break; // 조건을 만족한 경우 반복 종료
                }
            } 
            for (let x = 0; x < rankList[i].length; x++) {
                if (dateArray[4] === Number(rankList[i][x][2])) {
                    fiveDaysAgo = rankList[i][x][0];
                    break; // 조건을 만족한 경우 반복 종료
                }
            } 
            for (let x = 0; x < rankList[i].length; x++) {
                if (dateArray[3] === Number(rankList[i][x][2])) {
                    fourDaysAgo = rankList[i][x][0];
                    break; // 조건을 만족한 경우 반복 종료
                }
            } 
            for (let x = 0; x < rankList[i].length; x++) {
                if (dateArray[2] === Number(rankList[i][x][2])) {
                    threeDaysAgo = rankList[i][x][0];
                    break; // 조건을 만족한 경우 반복 종료
                }
            } 
            for (let x = 0; x < rankList[i].length; x++) {
                if (dateArray[1] === Number(rankList[i][x][2])) {
                    twoDaysAgo = rankList[i][x][0];
                    break; // 조건을 만족한 경우 반복 종료
                }
            }
            for (let x = 0; x < rankList[i].length; x++) {
                if (dateArray[0] === Number(rankList[i][x][2])) {
                    yesterday = rankList[i][x][0];
                    break; // 조건을 만족한 경우 반복 종료
                }
            }
            

            const ascendingData = [sevenDaysAgo,sixDaysAgo,fiveDaysAgo,fourDaysAgo,threeDaysAgo,twoDaysAgo,yesterday];
            const data = {
                labels: [dateArray[6],dateArray[5],dateArray[4],dateArray[3],dateArray[2],dateArray[1],dateArray[0]],
                datasets: [
                    {
                        label: '주간순위',
                        data: ascendingData,
                        borderColor: "red",
                        backgroundColor: "#FF8080",
                        pointStyle: 'circle',
                        pointRadius: 5,
                        pointHoverRadius: 15,
                    }
                ]
            };
            const config = {
                type: 'line',
                data: data,
                options: {
                    responsive: true,
                    plugins: {
                        title: {
                            display: true,
                        }
                    },
                    scales: {
                        x: {
                            suggestedMin: dateArray[6], // 7일 전 날짜
                            suggestedMax: dateArray[0],  // 1일 전 날짜
                        },
                        y: {
                            beginAtZero: false, // y축의 시작값을 0이 아닌 데이터 최소값으로 설정
                            suggestedMin: 1,  // y축 최솟값
                            suggestedMax: 11, // y축 최댓값
                            reverse: true, // 내림차순으로 표현
                            ticks: {
                                stepSize: 1, // 눈금 간격
                                min: 1,      // 최소 눈금 값
                                max: 11,     // 최대 눈금 값
                                callback: function(value, index, values) {
                                    return value === 11 ? '순위권밖' : value; // 11일 때 레이블 대체
                                }
                            }
                        }
                    }
                }
            };

            new Chart(rankChart, config)

        }

        btn[num[i]].addEventListener('click', PopUp)
    }

    // const top1 = document.getElementById("리스트0")
    // const top1Div = document.createElement("div")
    // top1Div.innerText = "1등!";
    // top1Div.setAttribute("id", "top1")
    // top1.appendChild(top1Div)


}
