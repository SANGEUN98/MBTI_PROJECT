import { formHidden, nextFormCrate } from "./NextFromCrate.js";
import { mbtiQuestionLode } from "./MbtiQuestion.js";

let a = 0
const pickMbtis = [];

export function pickList(e) {
    const pickMbti = [];
    const mbtiCheckBoxs = document.querySelectorAll('#mbti-question input')
    e.preventDefault();

    for (let k = 0; k < 5 ; k++) {
        for (let i = 0 + a; i < 7 + a; i++) {
            if (mbtiCheckBoxs[i].checked === true) {
                console.log(mbtiCheckBoxs[i]);
                pickMbti.push(mbtiCheckBoxs[i].name)
        }
    }
        if (!pickMbti[k]){
            pickMbti.push('3')
        }
        a += 7
}
    console.log(pickMbti);
   pickMbtis.push(pickMbti)
   formHidden(pickMbti, mbtiQuestionLode());
};





    


