export function MovieForm(lists) {
    
    console.log('연결')
    const form1 = document.getElementById('form1')
    const form2 = document.getElementById('form2')
    let abc = []
    let list = []
    for (let i = 0; i < lists.length; i++) {
        let abc = []
        abc.push(lists[i].url)
        abc.push(lists[i].name)
        abc.push(lists[i].opening)
        abc.push(lists[i].genre)
        abc.push(lists[i].information)
        list.push(abc)
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
        const input= document.createElement('input')

        li.setAttribute('id', `리스트${i}`)
        form1.appendChild(li)
        div1.setAttribute('name', '이미지')
        li.appendChild(div1);
        img1.setAttribute('src', `${list[i][0]}`)
        div1.appendChild(img1)
        div2.setAttribute('name', '영화제목')
        div2.textContent = `${list[i][1]}`
        li.appendChild(div2)
        div3.setAttribute('name', '개봉일')
        div3.textContent = `${list[i][2]}`
        li.appendChild(div3)
        div4.setAttribute('name', '장르')
        div4.textContent = `${list[i][3]}`
        li.appendChild(div4)
        div5.setAttribute('name', 'button')
        button1.setAttribute('name', 'button3')
        button1.textContent = '정보'
        li.appendChild(div5)
        div5.appendChild(button1)
        input.setAttribute('type', 'checkbox')
        div5.appendChild(input)


        function a (e) {
            console.log(e)
        }
        input.addEventListener('click', a)

        const div6 = document.createElement('div')
        const div7 = document.createElement('div')
        const div8 = document.createElement('div')
        const div9 = document.createElement('div')
        const div10 = document.createElement('div')
        const img2 = document.createElement('img')
        const p = document.createElement('p')
        div6.setAttribute('class', 'modalWrap')
        div6.setAttribute('id', `modalWrap${i}`)
        form2.appendChild(div6)
        div7.setAttribute('class', 'modalBody')
        div6.appendChild(div7)
        div7.appendChild(div8)

        // jQuery 코드
        // $(document).ready(function() {
        //     $('.circle').on('click', function(event) {
        //         event.preventDefault(event);  // 버튼 클릭 시 기본 동작(새로고침) 막기
        //         // 버튼을 눌렀을 때 수행할 동작 추가
        //         // 예를 들어, 모달을 닫도록 하는 코드 등을 여기에 추가
        //         const modalId = this.getAttribute('modal0'); // 모달 ID 가져오기
        //         const modal = document.getElementById(modal0);
        //         modal0[i].style.display = 'none'; // 모달 숨기기
        //         var animClass = $(this).data('animation');
        //         var removeTime = $(this).data('remove');

        //         if ($(this).hasClass(animClass)) {
        //             $(this).removeClass(animClass);
        //         } else {
        //             $(this).addClass(animClass);

        //             if (typeof removeTime !== 'undefined') {
        //                 var el = $(this);
        //                 setTimeout(function() {
        //                     el.removeClass(animClass);
        //                 }, removeTime);
        //             }
        //         }
        //     });
        // });

// 부모 컨테이너 엘리먼트 생성
        const circCont = document.createElement('div');
        circCont.classList.add('circCont');

// 버튼 엘리먼트 생성
        const button = document.createElement('button');
        button.classList.add('circle','custom-class');
        button.setAttribute('data-animation', 'simpleRotate');
        button.setAttribute('data-remove', '200');

// 버튼 클릭 시 동작 추가
        button.addEventListener('click', function(e) {
            e.preventDefault();
            modal0[i].style.display = 'none';
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
    }


    const modal0 = document.getElementsByClassName('modalWrap')
    const btn = document.querySelectorAll('#form1 button')

    const num = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    for (let i = 0; i < btn.length; i++) {

        function PopUp(e) {
            e.preventDefault();
            console.log('gd');
            modal0[i].style.display = 'block';
        }

        btn[num[i]].addEventListener('click', PopUp)
    }
}
