'use strict'

let stompClient

function createMessageLine(message){
    const chatCard = document.createElement('div')
    chatCard.className = 'card-body'
    const flexBox = document.createElement('div')
    const messageElement = document.createElement('div')
    messageElement.className = 'msg_container_send'
    messageElement.classList.add('chat-message')

    const avatarContainer = document.createElement('div')
    avatarContainer.className = 'img_cont_msg'
    const avatarElement = document.createElement('div')
    avatarElement.className = 'circle user_img_msg'


    flexBox.className = 'd-flex justify-content-end mb-4'
    messageElement.classList.add("mr-2")
    flexBox.appendChild(messageElement)
    flexBox.appendChild(avatarContainer)

    chatCard.appendChild(flexBox)


    messageElement.innerHTML = message

    const chat = document.querySelector('#chat')
    chat.appendChild(flexBox)
    chat.scrollTop = chat.scrollHeight
}


$(function (){
    const socket = new SockJS('/chat-example')
    stompClient = Stomp.over(socket) //У нас есть формат стандартного сообщения и поддержка на стороне клиента.
    stompClient.connect({}, onConnected, onError) //обработка соединения пользователя
})

const onConnected = options => {
    stompClient.subscribe('/topic', onMessageReceived)
}

const onError = (error) => {
    const status = document.querySelector('#status')
    status.innerHTML = 'Ошибка подключения! Перезагрузите страницу или попробуйте позже...'
    status.style.color = 'red'
}

const sendMessage = (event) => {
    const messageInput = document.querySelector('#message') //возвращает первого потомка запроса
    const messageContent = messageInput.value.trim(); //обрезаем пробелы

    if (messageContent && stompClient) {
        stompClient.send("/app/chat.send", {}, JSON.stringify(messageContent))
        messageInput.value = ''
    }
    event.preventDefault();
}


const onMessageReceived = (payload) => { //отправляем данные в теле json
    const message = JSON.parse(payload.body);
    createMessageLine(message);
}
var input = document.getElementById("message");

input.addEventListener("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault(); //сообщает пользовательскому агенту, что если событие не обрабатывается явно, его действие по умолчанию не должно выполняться, как обычно.
        sendMessage();
    }
});