console.log('ㅎㅇ1')
export function questionFormCreate(typeAll) {

const form = document.getElementById('mbti-question')
let mbtiText = []
for (const i in typeAll[0]) {
    for (const k in typeAll) {
        mbtiText.push(typeAll[k][i])
    }
}


let checkBoxNum = 0;
for (let i = 0; i < 5; i++) {
    
    
    const div = document.createElement('div');
    const div1 = document.createElement('div');
    const p = document.createElement('p');
    const b = document.createElement('b')
    const b1 = document.createElement('b')

    div.setAttribute('class', 'mbti-text');
    div.setAttribute('name', i+1);
    form.appendChild(div)
    
    p.textContent = `${mbtiText[i]}`
    div.appendChild(p)
    
    div1.setAttribute('class', 'mbti-check-boxs');
    form.appendChild(div1)
    
    b.textContent = '동의'
    div1.appendChild(b)

for (let i = 0; i < 7; i++) {
    const input = document.createElement('input')
    const label = document.createElement('label')
    input.setAttribute('type', 'checkbox')
    input.setAttribute('id', `mbti-check-box${i+checkBoxNum}`)
    input.setAttribute('name', i)
    label.setAttribute('name', i)
    label.setAttribute('for', `mbti-check-box${i+checkBoxNum}`)
    div1.appendChild(input)
    div1.appendChild(label)
}
    b1.textContent = '비동의'
    div1.appendChild(b1)
    checkBoxNum += 7
}

const mbtiCheckBoxs = document.querySelectorAll(".mbti-check-boxs")
for (let i = 0; i < mbtiCheckBoxs.length; i++) {
    function boxCheck(e) {
        let mbtiNumber = [1,3,5,7,9,11,13]
        for (const k of mbtiNumber) {   
            mbtiCheckBoxs[i].childNodes[k].checked = false;
        }
        e.target.checked = true;
    };
    mbtiCheckBoxs[i].addEventListener("change", boxCheck);
};
};

