<template>
	<div class="chat-container">
		<div class="all-div">
			<!--  头部区域  -->
			<div style="margin-left: 10px; font-size: 1.5em; font-weight: bold">
				戏曲网-在线聊天室
				<span class="nes-text is-primary">当前在线人数：</span>
				<span id="count" class="nes-text is-error">0</span>
			</div>
			<!--  内容显示区域  -->
			<div
				id="message"
				class="nes-container is-rounded is-dark message-list"
				style="margin-left: 10px; font-size: 1.2em"
			></div>
			<!--  操作区域  -->
			<div class="nes-field is-inline">
				<br />
				<input
					id="text"
					type="text"
					class="nes-input"
					style="padding: 0.2rem 1rem !important"
					@keyup.enter="send()"
				/>
				<button class="nes-btn is-success" @click="send()">发送</button>
				<button class="nes-btn is-error" @click="closeWebSocket()">
					关闭聊天室连接
				</button>
			</div>
		</div>
	</div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { SocketVO } from '~/api/types/websocket'
import { Playgoer } from '~/api/types/user'
import { getPlaygoer } from '~/api/user'

const playgoer = ref<Playgoer>({})
// Declare websocket as a ref
const websocket = ref<WebSocket | null>(null)
const token = localStorage.getItem('token')
const playgoerId = localStorage.getItem('playgoerId')

function isLogin() {
	console.log('isLogin')

	const token = localStorage.getItem('token')
	if (token === null || token === '' || token === undefined) {
		//未登录
		toast.warning('用户未登录，无法连接聊天室,请先登录!')
		return false
	} else {
		getPlaygoerById()
		return true
	}
}
function getPlaygoerById() {
	if (playgoerId === null || playgoerId === undefined || playgoerId === '') {
		return
	}
	getPlaygoer(playgoerId).then((res) => {
		playgoer.value = res.data
		console.log(playgoer.value)
	})
}
// 设置消息的innerHTML
const setMessageInnerHTML = (innerHTML: string) => {
	const messageDiv = document.getElementById('message')
	if (messageDiv) {
		messageDiv.innerHTML += innerHTML
	}
}

// 设置聊天计数的innerHTML
const setChatCountInnerHTML = (innerHTML: string | number) => {
	const countDiv = document.getElementById('count')
	if (countDiv) {
		countDiv.innerHTML = innerHTML.toString()
	}
}

// 检查浏览器是否支持WebSocket
if ('WebSocket' in window) {
	//判断用户是否登录
	if (isLogin()) {
		init()
	}
} else {
	alert('当前浏览器不支持 websocket')
	throw new Error('当前浏览器不支持 websocket')
}
// 初始化WebSocket
function init() {
	// 创建WebSocket对象
	websocket.value = new WebSocket('ws://127.0.0.1:5050/chat/' + token)

	// WebSocket打开回调
	websocket.value.onopen = () => {
		heartCheck.reset().start() //心跳检测重置
		console.log('llws连接成功!' + new Date().toUTCString())
		setMessageInnerHTML('聊天室连接成功<br>')
		setMessageInnerHTML('！！请文明发言，注意社交礼仪 ！！<br>')
	}

	// WebSocket消息接收回调
	websocket.value.onmessage = (event) => {
		const jsonData = event.data
		const data = JSON.parse(jsonData)
		console.log('Received message ==', event)
		if (data.type === '1') {
			console.log('data ==', data.body)

			heartCheck.reset().start() //如果获取到消息，说明连接是正常的，重置心跳检测
			const msg = otherPersonShowMsg(data.body)
			console.log('msg ==', msg)

			setMessageInnerHTML(msg)
		}
		if (data.type === '2') {
			heartCheck.reset().start() //如果获取到消息，说明连接是正常的，重置心跳检测
			setChatCountInnerHTML(data.body.count)
		}
		if (data.type === '3') {
			console.log('pong!')
			heartCheck.reset().start() //如果获取到消息，说明连接是正常的，重置心跳检测
		}
	}

	// WebSocket错误回调
	websocket.value.onerror = () => {
		setMessageInnerHTML('连接发生错误<br>')
		reconnect()
	}

	// WebSocket关闭回调
	websocket.value.onclose = () => {
		setMessageInnerHTML('连接关闭<br>')
		// reconnect()
	}
}

// 发送消息
const send = () => {
	const inputElement = document.getElementById(
		'text',
	) as HTMLInputElement | null
	if (
		inputElement &&
		websocket.value &&
		websocket.value.readyState === WebSocket.OPEN
	) {
		const message = inputElement.value
		websocket.value.send(JSON.stringify({ msg: message }))
		inputElement.value = ''
		const formattedMsg = meShowMsg(playgoer.value as Playgoer, message)
		console.log('formattedMsg ==', formattedMsg)

		setMessageInnerHTML(formattedMsg)
	}
}

// // 显示其他人发送的消息
// const otherPersonShowMsg = (body: SocketVO) => {
// 	return ` <section class="message-left">
//             <i class="nes-bcrikko"></i>
//             <div class="nes-balloon from-left is-dark" style="padding: .2rem 1rem !important;">
//               <p>${body.msg}</p>
//             </div>
//           </section>`
// }
// // 显示自己发送的消息
// const meShowMsg = (str: string) => {
// 	return ` <section class="message-right">
//               <div class="nes-balloon from-right is-dark" style="padding: .2rem 1rem !important;">
//                 <p>${str}</p>
//               </div>
//               <i class="nes-bcrikko"></i>
//           </section>`
// }

// 显示其他人发送的消息
const otherPersonShowMsg = (body: SocketVO) => {
	const avatar = document.createElement('img')
	avatar.setAttribute(
		'src',
		(body.playgoerAvatar as string) || 'src/assets/images/avatar.jpg',
	) // 使用默认头像图片
	avatar.setAttribute(
		'style',
		'width: 50px; height: 50px; margin-left: 20px; margin-right: 20px; border-radius: 50%;',
	)

	const username = document.createElement('p')
	username.textContent = body.playgoerName as string
	username.setAttribute('style', 'font-size: 0.8em; color: #888;')

	const message = document.createElement('p')
	message.textContent = body.msg

	const timestamp = document.createElement('p')
	timestamp.textContent = new Date(body.sendTime).toLocaleString()
	timestamp.setAttribute('style', 'font-size: 0.8em; color: #888;')

	const textContainer = document.createElement('div')
	textContainer.setAttribute(
		'style',
		'display: flex; flex-direction: column; align-items: flex-start; justify-content: space-between;',
	)
	textContainer.appendChild(username)
	textContainer.appendChild(message)
	textContainer.appendChild(timestamp)

	const balloon = document.createElement('div')
	balloon.setAttribute('class', 'nes-balloon from-left is-dark')
	balloon.setAttribute(
		'style',
		'padding: .2rem 1rem !important; display: flex; flex-direction: row; align-items: center; justify-content: space-between;',
	)
	balloon.appendChild(avatar)
	balloon.appendChild(textContainer)

	const section = document.createElement('section')
	section.setAttribute('class', 'message-left')
	section.appendChild(balloon)

	return section.outerHTML
}

// 显示自己发送的消息
const meShowMsg = (body: Playgoer, msg: string) => {
	const avatar = document.createElement('img')
	avatar.setAttribute(
		'src',
		(body.playgoerAvatar as string) || 'src/assets/images/avatar.jpg',
	) // 使用默认头像图片
	avatar.setAttribute(
		'style',
		'width: 50px; height: 50px; margin-right: 20px; border-radius: 50%;',
	)

	const message = document.createElement('p')
	message.textContent = msg

	const timestamp = document.createElement('p')
	timestamp.textContent = new Date().toLocaleString()
	timestamp.setAttribute('style', 'font-size: 0.8em; color: #888;')

	const textContainer = document.createElement('div')
	textContainer.setAttribute(
		'style',
		'display: flex; flex-direction: column; align-items: flex-end;',
	)
	textContainer.appendChild(message)
	textContainer.appendChild(timestamp)

	const balloon = document.createElement('div')
	balloon.setAttribute('class', 'nes-balloon from-right is-dark')
	balloon.setAttribute(
		'style',
		'padding: .2rem 1rem !important; display: flex; flex-direction: row-reverse; align-items: center;',
	)
	balloon.appendChild(avatar)
	balloon.appendChild(textContainer)

	const section = document.createElement('section')
	section.setAttribute('class', 'message-right')
	section.appendChild(balloon)

	return section.outerHTML
}

//心跳检测
const heartCheck = {
	timeout: 20000, //20s发一次心跳
	timeoutObj: null as number | null,
	serverTimeoutObj: null as number | null,
	reset: function () {
		if (this.timeoutObj !== null) {
			clearTimeout(this.timeoutObj)
		}
		if (this.serverTimeoutObj !== null) {
			clearTimeout(this.serverTimeoutObj)
		}
		return this
	},
	start: function () {
		let self = this
		this.timeoutObj = window.setTimeout(function () {
			//这里发送一个心跳，后端收到后，返回一个心跳消息，
			//onmessage拿到返回的心跳就说明连接正常
			if (websocket.value) {
				websocket.value.send(JSON.stringify('ping'))
				console.log('ping!')
				self.serverTimeoutObj = window.setTimeout(function () {
					//如果超过一定时间还没重置，说明后端主动断开了
					closeWebSocket()
				}, self.timeout)
			}
		}, this.timeout)
	},
}

//避免重复连接
const lockReconnect = ref(false)
const tt = ref()

/**
 * websocket重连
 */
function reconnect() {
	if (lockReconnect.value) {
		return
	}
	lockReconnect.value = true
	tt.value && clearTimeout(tt.value)
	tt.value = setTimeout(function () {
		console.log('重连中...')
		lockReconnect.value = false
		init()
		setMessageInnerHTML('重连中...<br>')
	}, 4000)
}

// 关闭WebSocket连接
const closeWebSocket = () => {
	if (websocket.value) {
		websocket.value.close()
		setChatCountInnerHTML(0)
	}
}
// 窗口关闭事件监听器，用于关闭WebSocket连接
window.onbeforeunload = () => {
	closeWebSocket()
}
// 使用 onBeforeUnmount 生命周期钩子来关闭WebSocket连接
onBeforeUnmount(() => {
	closeWebSocket()
})
</script>
<style src="~/styles/static/NES.css" scoped></style>
<style>
@font-face {
	font-family: 'pix';
	src: url('~/styles/static/DottedSongtiSquareRegular.otf');
}
/* 全局样式 */
html.v-global,
body.v-global,
pre.v-global,
code.v-global,
kbd.v-global,
samp.v-global {
	font-family: 'pix', serif !important; /* 使用 !important 来提高优先级 */
	font-weight: bold;
	font-size: 35px;
}

.all-div {
	display: flex;
	flex-direction: column;
	width: 800px;
	margin: 20px auto;
	overflow: auto;
	scroll-behavior: smooth;
}

.message {
	overflow: auto;
	width: 800px;
	height: 400px;
	margin-top: 20px;
}

.send-btns {
	display: flex;
}

/*自己发送聊天的样式*/
.message-me {
	color: red;
	text-align: right;
}

.message-list {
	display: flex;
	flex-direction: column;
}

.message-left {
	display: flex;
	margin-top: 2rem;
	align-self: flex-start;
}
.message-right {
	display: flex;
	margin-top: 2rem;
	align-self: flex-end;
}
.chat-container::after {
	content: '';
	background: url('~/assets/images/chat-bg.jpg') no-repeat center center;
	background-size: cover;
	opacity: 0.5;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	position: absolute;
	z-index: -1;
}
.chat-container {
	width: 100vw;
	height: calc(100vh - 80px);
	position: relative;
	display: flex;
	justify-content: center;
}
</style>
