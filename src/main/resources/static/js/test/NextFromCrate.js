import { mbtiQuestionLode } from "./MbtiQuestion.js";
import { mbtiResult } from "./Result.js";

let a = 5;

export function formHidden(e, mbtiTypeAll) {

    const formDiv = document.querySelectorAll('#mbti-question div')
       
    for (let i = 0; i < formDiv.length; i++) {
        formDiv[i].classList.add("hidden")
    }

    nextFormCrate(e, mbtiTypeAll)
};

const pickMbtis = []
let checkBoxNum = 35; 

export function nextFormCrate (e, mbtiTypeAll) {
    
    const form = document.getElementById('mbti-question')
    let mbtiText = []
    pickMbtis.push(e)
    
    
    if (a < 60) { 

        for (const i in mbtiTypeAll[0]) {
            for (const k in mbtiTypeAll) {
                mbtiText.push(mbtiTypeAll[k][i])
            }
        }
        for (let i = 0 + a; i < 5 + a; i++) {
            
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
    a +=5
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
}else{
    const ab = document.querySelector(".mbti-submit")
    ab.classList.add("hidden")
    mbtiResult(pickMbtis)
    }
};




