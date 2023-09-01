import { questionFormCreate } from "./QuestionFormCreate.js";
import { formHidden, nextFormCrate } from "./NextFromCrate.js";
import { mbtiQuestionLode } from "./MbtiQuestion.js";
import { pickList } from "./FickList.js";

    console.log('ㅎㅇ')
    const submit = document.querySelector(".mbti-submit")

    questionFormCreate(mbtiQuestionLode())

    submit.addEventListener("click", pickList)



