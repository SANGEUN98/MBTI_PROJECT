export function bestBookForm(lists) {
    const form1 = document.getElementById('form1')
    const form2 = document.getElementById('form2')
    console.log(lists);
    let abc = []
    let list = []
    for (let i = 0; i < lists.length; i++) {
        let abc = []
        abc.push(lists[i].img)
        abc.push(lists[i].title)
        abc.push(lists[i].author)
        abc.push(lists[i].rank)
        abc.push(lists[i].info)
        abc.push(lists[i].link)
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
        const button2 = document.createElement('button')

        li.setAttribute('id', `리스트${i}`)
        form1.appendChild(li)
        div1.setAttribute('name', '이미지')
        li.appendChild(div1);
        img1.setAttribute('src', `${list[i][0]}`)
        div1.appendChild(img1)
        div2.setAttribute('name', '제목')
        div2.textContent = `${list[i][1]}`
        li.appendChild(div2)
        div3.setAttribute('name', '작가')
        div3.textContent = `${list[i][2]}`
        li.appendChild(div3)
        div4.setAttribute('name', '순위')
        div4.textContent = `${list[i][3]}위`
        li.appendChild(div4)
        div5.setAttribute('name', 'button')
        button1.setAttribute('name', 'button1')
        button1.textContent = '구매하기'
        button2.setAttribute('name', 'button2')
        button2.textContent = '정보'
        li.appendChild(div5)
        div5.appendChild(button1)
        div5.appendChild(button2)

        button1.addEventListener('click', function(e) {
            e.preventDefault()
            window.open(`${list[i][5]}`, '_blank');
        });
        const div6 = document.createElement('div')
        const div7 = document.createElement('div')
        const div8 = document.createElement('div')
        const div9 = document.createElement('div')
        const div10 = document.createElement('div')
        const div11 = document.createElement('div')
        const buttonDiv = document.createElement('div')
        const button3 = document.createElement('button')
        const img2 = document.createElement('img')
        const p = document.createElement('p')

        div6.setAttribute('class', 'modalWrap')
        div6.setAttribute('id', `modalWrap${i}`)
        form2.appendChild(div6)
        div7.setAttribute('class', 'modalBody')
        div6.appendChild(div7)
        div7.appendChild(div8)
        buttonDiv.setAttribute("class", "cirCont")
        div8.appendChild(buttonDiv)

        button3.classList.add('circle', 'custom-class');
        button3.setAttribute("data-animation", "simpleRotate")
        button3.setAttribute("data-remove", "200")
        buttonDiv.appendChild(button3)

        button3.addEventListener('click', function(event) {
            event.preventDefault();  // 기본 동작(새로고침) 막기
            // 버튼을 눌렀을 때 수행할 동작 추가
            // 예를 들어, 모달을 닫도록 하는 코드 등을 여기에 추가
            const modalId = this.getAttribute('modal0'); // 모달 ID 가져오기
            const modal = document.getElementById(modal0);
            modal0[i].style.display = 'none'; // 모달 숨기기
        });

        div9.setAttribute('name', 'container')
        div7.appendChild(div9)
        div10.setAttribute("name", "popup-img")
        div9.appendChild(div10)
        div11.setAttribute("name", "줄거리")
        div9.appendChild(div11)
        img2.setAttribute('src', `${list[i][0]}`)
        img2.setAttribute("name", "form2Image")
        div10.appendChild(img2)
        div10.setAttribute('name', 'popup-img')
        p.textContent = `${list[i][4]}`
        p.setAttribute("name", "inform")
        div11.appendChild(p)
        

    }


    const modal0 = document.getElementsByClassName('modalWrap')
    const btn = document.querySelectorAll('#form1 button')

    const num = [1, 3, 5, 7, 9, 11, 13, 15, 17, 19]
    for (let i = 0; i < 10; i++) {

        function PopUp(e) {
            e.preventDefault();
            console.log('gd');
            modal0[i].style.display = 'block';
        }

        btn[num[i]].addEventListener('click', PopUp)
    }
}
