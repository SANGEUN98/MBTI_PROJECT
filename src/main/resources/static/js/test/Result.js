import { webSocket } from "./WebSocket.js";
import { resultFage } from "./ResultFage.js";

export function mbtiResult(e) {
    console.log(e);

let defaultScoreE = 53
let defaultScoreS = 53
let defaultScoreF = 51
let defaultScoreP = 51
let defaultScore_t = 51


let resultType1 = [];
let resultType2 = [];
let resultType3 = [];
let resultType4 = [];
let resultType5 = [];



for (let i = 0; i < e.length; i++) {
    for (let k = 0; k < e[0].length; k++) {

        if (k === 0) {
            resultType1.push(e[i][k])
        }else if (k === 1){
            resultType2.push(e[i][k])
        }else if (k === 2){
            resultType3.push(e[i][k])
        }else if (k === 3){
            resultType4.push(e[i][k])
        }else if (k === 4){
            resultType5.push(e[i][k])
        }
    }
}

let aCase = [0,2,3,7,9,11];
let bCase = [1,4,5,6,8,10];

let resultCase1 = [];

for (let i = 0; i < resultType1.length; i++) {
    resultCase1 = resultType1.map(Number);
}


for (const i of aCase) {
    if (resultCase1[i] === 0) {
        defaultScoreE+=4
    } else if (resultCase1[i] === 1){
        defaultScoreE+=3
    } else if (resultCase1[i] === 2) {
        defaultScoreE+=2
    } else if (resultCase1[i] === 3) {
        defaultScoreE+=0
    } else if (resultCase1[i] === 4) {
        defaultScoreE-=2
    } else if (resultCase1[i] === 5) {
        defaultScoreE-=3
    } else if (resultCase1[i] === 6) {
        defaultScoreE-=4
    }
}
for (const i of bCase) {
    if (resultCase1[i] === 0) {
        defaultScoreE-=4
    }else if(resultCase1[i] === 1){
        defaultScoreE-=3
    } else if (resultCase1[i] === 2) {
        defaultScoreE-=2
    } else if (resultCase1[i] === 3) {
        defaultScoreE-=0
    } else if (resultCase1[i] === 4) {
        defaultScoreE+=2
    } else if (resultCase1[i] === 5) {
        defaultScoreE+=3
    } else if (resultCase1[i] === 6) {
        defaultScoreE+=4
    }
    
}






let cCase = [1, 4, 6, 7, 9, 10];
let dCase = [0, 2, 3, 5, 7, 8];

let resultCase2 = [];

for (let i = 0; i < resultType2.length; i++) {
    resultCase2 = resultType2.map(Number);
}


for (const i of cCase) {
    if (resultCase2[i] === 0) {
        defaultScoreS += 4
    } else if (resultCase2[i] === 1) {
        defaultScoreS += 3
    } else if (resultCase2[i] === 2) {
        defaultScoreS += 2
    } else if (resultCase2[i] === 3) {
        defaultScoreS += 0
    } else if (resultCase2[i] === 4) {
        defaultScoreS -= 2
    } else if (resultCase2[i] === 5) {
        defaultScoreS -= 3
    } else if (resultCase2[i] === 6) {
        defaultScoreS -= 4
    }
}
for (const i of dCase) {
    if (resultCase2[i] === 0) {
        defaultScoreS -= 4
    } else if (resultCase2[i] === 1) {
        defaultScoreS -= 3
    } else if (resultCase2[i] === 2) {
        defaultScoreS -= 2
    } else if (resultCase2[i] === 3) {
        defaultScoreS -= 0
    } else if (resultCase2[i] === 4) {
        defaultScoreS += 2
    } else if (resultCase2[i] === 5) {
        defaultScoreS += 3
    } else if (resultCase2[i] === 6) {
        defaultScoreS += 4
    }
}



    let eCase = [0, 1, 3, 7, 9, 10, 11];
    let fCase = [2, 4, 5, 6, 8];

    let resultCase3 = [];

    for (let i = 0; i < resultType3.length; i++) {
        resultCase3 = resultType3.map(Number);
    }


    for (const i of eCase) {
        if (resultCase3[i] === 0) {
            defaultScoreF += 4
        } else if (resultCase3[i] === 1) {
            defaultScoreF += 3
        } else if (resultCase3[i] === 2) {
            defaultScoreF += 2
        } else if (resultCase3[i] === 3) {
            defaultScoreF += 0
        } else if (resultCase3[i] === 4) {
            defaultScoreF -= 2
        } else if (resultCase3[i] === 5) {
            defaultScoreF -= 3
        } else if (resultCase3[i] === 6) {
            defaultScoreF -= 4
        }
    }
    for (const i of fCase) {
        if (resultCase3[i] === 0) {
            defaultScoreF -= 4
        } else if (resultCase3[i] === 1) {
            defaultScoreF -= 3
        } else if (resultCase3[i] === 2) {
            defaultScoreF -= 2
        } else if (resultCase3[i] === 3) {
            defaultScoreF -= 0
        } else if (resultCase3[i] === 4) {
            defaultScoreF += 2
        } else if (resultCase3[i] === 5) {
            defaultScoreF += 3
        } else if (resultCase3[i] === 6) {
            defaultScoreF += 4
        }

    }



    let gCase = [3, 5, 6, 9, 11];
    let hCase = [0, 1, 2, 4, 7, 8, 10];

    let resultCase4 = [];

    for (let i = 0; i < resultType4.length; i++) {
        resultCase4 = resultType4.map(Number);
    }



    for (const i of gCase) {
        if (resultCase4[i] === 0) {
            defaultScoreP += 4
        } else if (resultCase4[i] === 1) {
            defaultScoreP += 3
        } else if (resultCase4[i] === 2) {
            defaultScoreP += 2
        } else if (resultCase4[i] === 3) {
            defaultScoreP += 0
        } else if (resultCase4[i] === 4) {
            defaultScoreP -= 2
        } else if (resultCase4[i] === 5) {
            defaultScoreP -= 3
        } else if (resultCase4[i] === 6) {
            defaultScoreP -= 4
        }
    }
    for (const i of hCase) {
        if (resultCase4[i] === 0) {
            defaultScoreP -= 4
        } else if (resultCase4[i] === 1) {
            defaultScoreP -= 3
        } else if (resultCase4[i] === 2) {
            defaultScoreP -= 2
        } else if (resultCase4[i] === 3) {
            defaultScoreP -= 0
        } else if (resultCase4[i] === 4) {
            defaultScoreP += 2
        } else if (resultCase4[i] === 5) {
            defaultScoreP += 3
        } else if (resultCase4[i] === 6) {
            defaultScoreP += 4
        }

    }



    let iCase = [1, 3, 4 ,7, 8, 9, 10];
    let jCase = [0, 2, 5, 6, 11];

    let resultCase5 = [];

    for (let i = 0; i < resultType5.length; i++) {
        resultCase5 = resultType5.map(Number);
    }



    for (const i of iCase) {
        if (resultCase5[i] === 0) {
            defaultScore_t += 4
        } else if (resultCase5[i] === 1) {
            defaultScore_t += 3
        } else if (resultCase5[i] === 2) {
            defaultScore_t += 2
        } else if (resultCase5[i] === 3) {
            defaultScore_t += 0
        } else if (resultCase5[i] === 4) {
            defaultScore_t -= 2
        } else if (resultCase5[i] === 5) {
            defaultScore_t -= 3
        } else if (resultCase5[i] === 6) {
            defaultScore_t -= 4
        }
    }
    for (const i of jCase) {
        if (resultCase5[i] === 0) {
            defaultScore_t -= 4
        } else if (resultCase5[i] === 1) {
            defaultScore_t -= 3
        } else if (resultCase5[i] === 2) {
            defaultScore_t -= 2
        } else if (resultCase5[i] === 3) {
            defaultScore_t -= 0
        } else if (resultCase5[i] === 4) {
            defaultScore_t += 2
        } else if (resultCase5[i] === 5) {
            defaultScore_t += 3
        } else if (resultCase5[i] === 6) {
            defaultScore_t += 4
        }
        
    }

let mbti_I;
let mbti_E;
let mbti_N;
let mbti_S;
let mbti_T;
let mbti_F;
let mbti_P;
let mbti_J;
let mbti_t;
let mbti_a;

if (defaultScoreE > 50) {
    mbti_E = defaultScoreE;
    mbti_I = 100 - defaultScoreE;
}else{
    mbti_E = defaultScoreE;
    mbti_I = 100 - defaultScoreE;
};



if (defaultScoreS > 50) {
    mbti_S = defaultScoreS;
    mbti_N = 100 - defaultScoreS;
}else{
    mbti_S = defaultScoreS;
    mbti_N = 100 - defaultScoreS;
};

if (defaultScoreF > 50) {
    mbti_F = defaultScoreF;
    mbti_T = 100 - defaultScoreF;
}else{
    mbti_F = defaultScoreF;
    mbti_T = 100 - defaultScoreF;
};

if (defaultScoreP > 50) {
    mbti_P = defaultScoreP;
    mbti_J = 100 - defaultScoreP;
}else{
    mbti_P = defaultScoreP;
    mbti_J = 100 - defaultScoreP;
};

    if (defaultScore_t > 50) {
        mbti_t = defaultScore_t;
        mbti_a = 100 - defaultScore_t;
    } else {
        mbti_t = defaultScore_t;
        mbti_a = 100 - defaultScore_t;
    };



console.log(mbti_E);
console.log(mbti_I);
console.log(mbti_S);
console.log(mbti_N);
console.log(mbti_T);
console.log(mbti_F);
console.log(mbti_J);
console.log(mbti_P);
console.log(mbti_t);
console.log(mbti_a);

const MBTI = [];
if (mbti_E > mbti_I) {
MBTI.push("E")
}else{
MBTI.push("I")
};

if (mbti_S > mbti_N) {
MBTI.push("S")
}else{
MBTI.push("N")
};

if (mbti_F > mbti_T) {
MBTI.push("F")
}else{
MBTI.push("T")
}

if (mbti_P > mbti_J) {
MBTI.push("P")
}else{
MBTI.push("J")
}

if (mbti_t > mbti_a) {
MBTI.push("-t")
}else{
MBTI.push("-a")
}

let resultMbti = ""
for (const i of MBTI) {
   resultMbti += i; 
}

const resultMbtis = {
    mbti:resultMbti,
    E:mbti_E,
    I:mbti_I,
    S:mbti_S,
    N:mbti_N,
    T:mbti_T,
    F:mbti_F,
    P:mbti_P,
    J:mbti_J,
    a:mbti_a,
    t:mbti_t
};


resultFage(resultMbtis)

webSocket(e,resultMbti);
}













