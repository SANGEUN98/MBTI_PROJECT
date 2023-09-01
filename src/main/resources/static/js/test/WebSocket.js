import { mbtiResult } from "./Result.js";

export function webSocket(e,mbti) {
    
    const socket = new WebSocket("ws://192.168.30.12:8060");

socket.onopen = () => {

    let mbtiList = [];
    console.log("웹소켓 연결!");
    console.log(e);
    console.log(mbti);
    mbtiList.push(e,mbti)
    console.log(mbtiList);

    
    socket.send(mbtiList);

};
};