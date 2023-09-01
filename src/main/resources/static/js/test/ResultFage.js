export function resultFage(resultMbtis) {
    const p = document.createElement('p')
    const form = document.getElementById('mbti-question')
    console.log(resultMbtis);
    
    p.setAttribute('class', 'mbti-result')
    p.innerHTML = `당신의 mbti는 입니다`
    form.appendChild(p)

}