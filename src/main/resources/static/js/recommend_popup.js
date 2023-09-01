export function DataForm(lists) {
    const form1 = document.getElementById('form1');
    const form2 = document.getElementById('form2');
    let list = [];
    console.log(lists)
    Object.keys(lists).forEach(movieTitle => {
        lists[movieTitle].forEach(movieInfo => {
            const movieName = movieInfo[0];
            const similarityScore = movieInfo[1];
            const genres = movieInfo[2];
            const info = movieInfo[4];
            const imageUrl = movieInfo[3];
            list.push([movieName, similarityScore, imageUrl, genres, info]);
        });
    });


    for (let i = 0; i < list.length; i++) {
        const li = document.createElement('li');
        const div1 = document.createElement('div');
        const div2 = document.createElement('div');
        const div3 = document.createElement('div');
        const div4 = document.createElement('div');
        const img = document.createElement('img');
        const button1 = document.createElement('button')
        div4.setAttribute("name", "button")
        button1.setAttribute('name', 'button3')
        button1.setAttribute('name', 'button3')
        button1.textContent = '정보'
        console.log(list[i]);
        div1.textContent = list[i][0];
        div2.textContent = list[i][1];
        div2.textContent = list[i][3];
        img.src = list[i][2];

        li.appendChild(img);
        li.appendChild(div1);
        li.appendChild(div2);
        li.appendChild(div3);
        li.appendChild(div4);
        div4.appendChild(button1);
        form1.appendChild(li);
        const modal0 = document.getElementsByClassName('modalWrap')
        const btn = document.querySelectorAll('#form1 button')
        console.log(btn)
        function PopUp(e) {
            e.preventDefault();
            console.log('gd');
            modal0[i].style.display = 'block';
        }


        btn[i].addEventListener('click', PopUp)

        const div6 = document.createElement('div')
        const div7 = document.createElement('div')
        const div8 = document.createElement('div')
        const div9 = document.createElement('div')
        const div10 = document.createElement('div')
        const button3 = document.createElement('button')
        const img2 = document.createElement('img')
        const p = document.createElement('p')

        div6.setAttribute('class', 'modalWrap')
        div6.setAttribute('id', `modalWrap${i}`)
        form2.appendChild(div6)
        div7.setAttribute('class', 'modalBody')
        div6.appendChild(div7)
        div7.appendChild(div8)
        button3.textContent = 'x'
        button3.addEventListener('click', function(event) {
            event.preventDefault();  // 기본 동작(새로고침) 막기
            // 버튼을 눌렀을 때 수행할 동작 추가
            // 예를 들어, 모달을 닫도록 하는 코드 등을 여기에 추가
            const modalId = this.getAttribute('modal0'); // 모달 ID 가져오기
            const modal = document.getElementById(modal0);
            modal0[i].style.display = 'none'; // 모달 숨기기
        });
        div8.appendChild(button3)
        div9.setAttribute('name', 'popup-img')
        div7.appendChild(div9)
        img2.setAttribute('src', `${list[i][2]}`)
        div9.appendChild(img2)
        div10.setAttribute('name', '줄거리')
        div7.appendChild(div10)
        p.textContent = `${list[i][4]}`
        div10.appendChild(p)
    }

    // function PopUp(e) {
    //     e.preventDefault();
    //     console.log('gd');
    //     modal0[i].style.display = 'block';
    // }
    //
    // button1.addEventListener('click', PopUp)
}
