export function chart(list) {

// mbti 널 값 발생시 리스트 제외
let mbtiList = [];
list.forEach(element => {
    if (element["mbti"] !== null){
        mbtiList.push(element)
    }
});
console.log(mbtiList);
    // mbti count
    let I1 = 0
    let E1 = 0
    let N1 = 0
    let S1 = 0
    let F1 = 0
    let T1 = 0
    let P1 = 0
    let J1 = 0

    for (let i = 0; i < mbtiList.length; i++) {
        let I = 0
        let E = 0
        let N = 0
        let S = 0
        let F = 0
        let T = 0
        let P = 0
        let J = 0
        for (let j = 0; j < 4; j++) {
            if (mbtiList[i].mbti[j] == "I"){
                I += 1
            } else if (mbtiList[i].mbti[j] == "E"){
                E += 1
            } else if (mbtiList[i].mbti[j] == "N"){
                N += 1
            } else if (mbtiList[i].mbti[j] == "S"){
                S += 1
            } else if (mbtiList[i].mbti[j] == "F"){
                F += 1
            } else if (mbtiList[i].mbti[j] == "T") {
                T += 1
            } else if (mbtiList[i].mbti[j] == "P") {
                P += 1
            } else if (mbtiList[i].mbti[j] == "J") {
                J += 1
            }
        }
        // 유형 * 유형 수
        I1 += I * mbtiList[i].cnt
        E1 +=E * mbtiList[i].cnt
        N1 +=N * mbtiList[i].cnt
        S1 +=S * mbtiList[i].cnt
        F1 +=F * mbtiList[i].cnt
        T1 +=T * mbtiList[i].cnt
        P1 +=P * mbtiList[i].cnt
        J1 +=J * mbtiList[i].cnt
    }
        
        console.log("i = " + I1)
        console.log("e = " + E1)
        console.log("n = " + N1)
        console.log("s = " + S1)
        console.log("f = " + F1)
        console.log("t = " + T1)
        console.log("p = " + P1)
        console.log("j = " + J1)
        
    
        // 차트 플러그인 옵션
        let chartDataLabelsOptions = {
            plugins: {
                responsive: false,
                maintainAspectRatio: false,
                datalabels: {
                    font: { size: 18, weight: 'bold' },
                    color: 'gray',
                    formatter: function (value, context) {
                        if (value === 0){
                            value = "";
                            return value;
                        }
                        console.log(mbtiList.length);
                        let a = value * 100 / mbtiList.length;
                        return a+"%";
                    },
                }
            }
        };
    
    var ctx1 = document.getElementById('chart1').getContext('2d');
    new Chart(ctx1, {
        plugins: [ChartDataLabels],
        type: 'bar',
        data: {
            labels: mbtiList.map(mbti => mbti.mbti),
            datasets: [{
                data: mbtiList.map((mbti=>mbti.cnt)),
                backgroundColor: [
                    'rgba(204,102,102,0.2)', 'rgba(102,153,153,0.2)', 'rgba(255,153,153,0.2)', 'rgba(102,204,204,0.2)',
                    'rgba(153,204,204,0.2)', 'rgba(51,153,102,0.2)', 'rgba(153,255,255,0.2)', 'rgba(102,204,153,0.2)',
                    'rgba(204,153,153,0.2)', 'rgba(255,204,204,0.2)', 'rgba(51,204,153,0.2)', 'rgba(102,255,204,0.2)',
                    'rgba(204,153,51,0.2)', 'rgba(204,153,102,0.2)', 'rgba(255,204,102,0.2)', 'rgba(255,204,153,0.2)',
                ],
                borderColor: [
                    'rgba(204,102,102,1)', 'rgba(102,153,153,1)', 'rgba(255,153,153,1)', 'rgba(102,204,204,1)',
                    'rgba(153,204,204,1)', 'rgba(51,153,102,1)', 'rgba(153,255,255,1)', 'rgba(102,204,153,1)',
                    'rgba(204,153,153,1)', 'rgba(255,204,204,1)', 'rgba(51,204,153,1)', 'rgba(102,255,204,1)',
                    'rgba(204,153,51,1)', 'rgba(204,153,102,1)', 'rgba(255,204,102,1)', 'rgba(255,204,153,1)',
                ],
                borderWidth: 1
            }]
        },
        options: {
        plugins: { legend: { display: false } }
    }
});

var ctx2 = document.getElementById('chart2').getContext('2d');
new Chart(ctx2, {
    plugins: [ChartDataLabels],
    type: 'pie',
    data: {
        labels: ['E', 'I'],
        datasets: [{
            data: [E1, I1],
            backgroundColor: ['rgba(137,207,240,0.2)', 'rgba(188,212,230,0.2)'],
            borderColor: ['rgba(137,207,240,0.5)', 'rgba(188,212,230,0.5)'],
            borderWidth: 3,
        }]
    },
    options: chartDataLabelsOptions
});


var ctx3 = document.getElementById('chart3').getContext('2d');
new Chart(ctx3, {
    plugins: [ChartDataLabels],
    type: 'pie',
    data: {
        labels: ['N', 'S'],
        datasets: [{
            data: [N1,S1],
            backgroundColor: ['rgba(255,255,102,0.2)', 'rgba(255,255,153,0.2)'],
            borderColor: ['rgba(255,255,102,0.5)', 'rgba(255,255,153,0.5)'],
            borderWidth: 3,
        }]
    },
    options: chartDataLabelsOptions
});


var ctx4 = document.getElementById('chart4').getContext('2d');
new Chart(ctx4, {
    plugins: [ChartDataLabels],
    type: 'pie',
    data: {
        labels: ['T', 'F'],
        datasets: [{
            data: [T1, F1],
            backgroundColor: ['rgba(102,204,153,0.2)', 'rgba(153,255,204,0.2)'],
            borderColor: ['rgba(102,204,153,0.5)', 'rgba(153,255,204,0.5)'],
            borderWidth: 3,
        }]
    },
    options: chartDataLabelsOptions
});

var ctx5 = document.getElementById('chart5').getContext('2d');
new Chart(ctx5, {
    plugins: [ChartDataLabels],
    type: 'pie',
    data: {
        labels: ['P', 'J'],
        datasets: [{
            data: [P1, J1],
            backgroundColor: ['rgba(255,153,051,0.2)', 'rgba(255,153,102,0.2)'],
            borderColor: ['rgba(255,153,051,0.5)', 'rgba(255,153,102,0.5)'],
            borderWidth: 3,
        }]
    },
    options: chartDataLabelsOptions
});
}
