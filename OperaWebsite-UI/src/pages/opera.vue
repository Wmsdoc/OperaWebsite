<template>
	<div class="opera-container">
		<div style="max-width: 60%; margin: auto">
			<div v-if="flag === 'audio'">
				<audio :src="audioDetails.downloadUrl" controls></audio>
			</div>
			<div v-else-if="flag === 'video'" ref="playerElement"></div>

			<el-descriptions
				v-if="flag === 'audio'"
				title="戏曲音频详情"
				class="margin-top"
				:column="3"
				:size="size"
				direction="vertical"
				border
			>
				<template #extra>
					<el-button
						v-if="collectionFlag"
						@click="deleteCollection(flag, audioDetails.audioId)"
						type="primary"
						>已收藏</el-button
					>
					<el-button
						v-else="collectionFlag"
						@click="insertCollection(flag, audioDetails.audioId)"
						type="primary"
						>收藏</el-button
					>
					<el-button
						type="primary"
						@click="
							downloadAudio(audioDetails.audioId, audioDetails.downloadUrl)
						"
						>下载</el-button
					>
				</template>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">音频名称</div>
					</template>
					{{ audioDetails.filename }}
				</el-descriptions-item>

				<el-descriptions-item>
					<template #label>
						<div class="cell-item">类别</div>
					</template>
					<el-tag size="small">{{ audioDetails.typeName }}</el-tag>
				</el-descriptions-item>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">戏曲简介</div>
					</template>
					{{ audioDetails.audioInfo }}
				</el-descriptions-item>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">下载量</div>
					</template>
					{{ audioDetails.downloadNum }}
				</el-descriptions-item>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">收藏量</div>
					</template>
					{{ collectionNum }}
				</el-descriptions-item>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">上传者</div>
					</template>
					{{ audioDetails.playgoerName }}
				</el-descriptions-item>

				<el-descriptions-item>
					<template #label>
						<div class="cell-item">上传时间</div>
					</template>
					{{ audioDetails.createdAt }}
				</el-descriptions-item>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">上传者签名</div>
					</template>
					{{ audioDetails.playgoerInfo }}
				</el-descriptions-item>
			</el-descriptions>
			<el-descriptions
				v-else-if="flag === 'video'"
				class="margin-top"
				title="戏曲视频详情"
				:column="3"
				:size="size"
				direction="vertical"
				border
			>
				<template #extra>
					<el-button
						v-if="collectionFlag"
						@click="deleteCollection(flag, videoDetails.videoId)"
						type="primary"
						>已收藏</el-button
					>
					<el-button
						v-else="collectionFlag"
						@click="insertCollection(flag, videoDetails.videoId)"
						type="primary"
						>收藏</el-button
					>
					<el-button
						type="primary"
						@click="
							downloadVideo(videoDetails.videoId, videoDetails.downloadUrl)
						"
						>下载</el-button
					>
				</template>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">视频名称</div>
					</template>
					{{ videoDetails.filename }}
				</el-descriptions-item>

				<el-descriptions-item>
					<template #label>
						<div class="cell-item">类别</div>
					</template>
					<el-tag size="small">{{ videoDetails.typeName }}</el-tag>
				</el-descriptions-item>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">戏曲简介</div>
					</template>
					{{ videoDetails.videoInfo }}
				</el-descriptions-item>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">下载量</div>
					</template>
					{{ videoDetails.downloadNum }}
				</el-descriptions-item>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">收藏量</div>
					</template>
					{{ collectionNum }}
				</el-descriptions-item>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">上传者</div>
					</template>
					{{ videoDetails.playgoerName }}
				</el-descriptions-item>

				<el-descriptions-item>
					<template #label>
						<div class="cell-item">上传时间</div>
					</template>
					{{ videoDetails.createdAt }}
				</el-descriptions-item>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">上传者签名</div>
					</template>
					{{ videoDetails.playgoerInfo }}
				</el-descriptions-item>
			</el-descriptions>
			<el-divider></el-divider>
			<!-- 评论 -->
			<p class="comment-section-title">评论区</p>
			<el-row v-if="loginFlag">
				<el-col :span="16">
					<el-input
						v-model="textarea"
						:rows="2"
						autosize
						maxlength="200"
						show-word-limit
						type="textarea"
						placeholder="请留下你的评论"
					/>
				</el-col>
				<el-col :span="7" style="text-align: right">
					<el-button @click="insertComment">发布</el-button>
				</el-col>
			</el-row>
			<el-row v-else>
				<el-col :span="16">
					<el-input
						v-model="textarea"
						:rows="2"
						autosize
						maxlength="200"
						show-word-limit
						disabled
						type="textarea"
						placeholder="请登录后再评论"
					/>
				</el-col>
				<el-col :span="7" style="text-align: right">
					<el-button @click="tologin">去登录</el-button>
				</el-col>
			</el-row>

			<ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto">
				<li v-for="item in operaComments" class="infinite-list-item">
					<el-avatar
						@click="commentClick(item.playgoerId)"
						:src="item.playgoerAvatar"
					/>
					{{ item.playgoerName }}:
					{{ item.commentInfo }}
					{{ item.updatedAt }}
				</li>
			</ul>
		</div>
	</div>
</template>

<script setup lang="ts">
import { OperaAudioVO } from '~/api/types/audio'
import { OperaVideoVO } from '~/api/types/video'
import { OperaComment } from '~/api/types/opera'
import router from '~/plugins/router'
import Player from 'xgplayer'
import 'xgplayer/dist/index.min.css'
import 'xgplayer-music/dist/index.min.css'

const size = 'large'

const Route = useRoute() //获取到值
const flag = ref<string>('audio') //判断是音频还是视频
const audioId = ref<number>(0) //音频id
const videoId = ref<number>(0) //视频id
const loginFlag = ref<boolean>(false) //判断用户是否登录
const collectionFlag = ref() //判断用户是否已收藏

const textarea = ref<string>('') //评论内容

//详情数据
const audioDetails = ref<OperaAudioVO>({})
const videoDetails = ref<OperaVideoVO>({})
const collectionNum = ref<number>(0)
const operaComments = ref<OperaComment[]>()

const page = ref<number>(0) //标记limit
const count = ref(0)
const load = () => {
	count.value += 2
	if (count.value >= 10) {
		getComment(page.value)
	}
}

function tologin() {
	router.push({ path: '/login' })
}

function insertComment() {
	if (flag.value === 'audio') {
		insertAudioComment(audioId.value, textarea.value).then((res) => {
			if (res) {
				toast.success('评论成功')
				page.value = 0
				operaComments.value = undefined
				//清空input
				textarea.value = ''
				getComment(page.value)
			} else {
				toast.error('评论失败')
			}
		})
	} else if (flag.value === 'video') {
		insertVideoComment(videoId.value, textarea.value).then((res) => {
			if (res) {
				toast.success('评论成功')
				page.value = 0
				operaComments.value = undefined
				//清空input
				textarea.value = ''
				getComment(page.value)
			} else {
				toast.error('评论失败')
			}
		})
	}
}

function deleteCollection(flag: string, id: any) {
	//判断用户是否登录
	const token = localStorage.getItem('token')
	if (token === null || token === '' || token === undefined) {
		//未登录
		toast.warning('请先登录')
		return
	}
	//删除收藏
	const playgoerId = localStorage.getItem('playgoerId')
	if (flag === 'audio') {
		deleteAudioCollection(id, playgoerId).then((res) => {
			if (res) {
				toast.success('取消收藏成功')
				collectionFlag.value = false
			} else {
				toast.error('取消收藏失败')
			}
		})
	} else if (flag === 'video') {
		deleteVideoCollection(id, playgoerId).then((res) => {
			if (res) {
				toast.success('取消收藏成功')
				collectionFlag.value = false
			} else {
				toast.error('取消收藏失败')
			}
		})
	}
}
function insertCollection(flag: string, id: any) {
	//判断用户是否登录
	const token = localStorage.getItem('token')
	if (token === null || token === '' || token === undefined) {
		//未登录
		toast.warning('请先登录')
		return
	}
	//添加收藏
	const playgoerId = localStorage.getItem('playgoerId')
	if (flag === 'audio') {
		insertAudioCollection(id, playgoerId).then((res) => {
			if (res) {
				toast.success('收藏成功')
				collectionFlag.value = true
			} else {
				toast.error('收藏失败')
			}
		})
	} else if (flag === 'video') {
		insertVideoCollection(id, playgoerId).then((res) => {
			if (res) {
				toast.success('收藏成功')
				collectionFlag.value = true
			} else {
				toast.error('收藏失败')
			}
		})
	}
}

const playerInstance = ref<Player | null>(null)
const playerElement = ref<HTMLElement | null>(null)
function getOperaDetails(Route: any) {
	//获取到id
	//根据id获取到数据
	//返回数据
	if (Route.query.type === 'audio') {
		audioId.value = Route.query.id
		getAudioDetails(audioId.value)
			.then((res) => {
				audioDetails.value = res.data
				// if (playerElement.value) {
				// 	const player = new Player({
				// 		el: playerElement.value,
				// 		video: null,
				// 		mediaType: 'audio', // 设置媒体类型
				// 		volume: 0.8,
				// 		width: 900,
				// 		height: 50, // 设置音频播放器的高度
				// 		playsinline: true, // 在移动设备上播放时内联播放
				// 		playbackRate: [0.5, 1, 1.5, 2], // 设置音频播放器的播放速度
				// 		url: audioDetails.value.downloadUrl,
				// 	})
				// 	playerInstance.value = player
				// }
			})
			.then(() => {
				//获取收藏量
				getAudioCollectionCount(audioId.value)
					.then((res) => {
						collectionNum.value = res.data
					})
					.catch((err) => {
						console.log(err)
					})
			})
			.catch((err) => {
				console.log(err)
			})
	} else if (Route.query.type === 'video') {
		flag.value = 'video'
		videoId.value = Route.query.id
		getVideoDetails(videoId.value)
			.then((res) => {
				videoDetails.value = res.data
				if (playerElement.value) {
					playerInstance.value = new Player({
						el: playerElement.value,
						volume: 0.5,
						width: 600,
						height: 337,
						fluid: true,
						screenShot: true,
						download: true,
						pip: true,
						keyShortcut: true,
						playsinline: true,
						playbackRate: [0.5, 1, 1.5, 2],
						url: videoDetails.value.downloadUrl,
					})
				}
			})
			.then(() => {
				//获取收藏量
				getVideoCollectionCount(videoId.value)
					.then((res) => {
						collectionNum.value = res.data
					})
					.catch((err) => {
						console.log(err)
					})
			})
			.catch((err) => {
				console.log(err)
			})
	}
}

function getComment(page: number) {
	//获取评论
	if (flag.value === 'audio') {
		getAudioComment(audioId.value, page)
			.then((res) => {
				if (operaComments.value === undefined) {
					operaComments.value = res.data
				} else {
					operaComments.value?.join(...res.data)
				}
				page += 10
			})
			.catch((err) => {
				console.log(err)
			})
	} else if (flag.value === 'video') {
		getVideoComment(videoId.value, page)
			.then((res) => {
				operaComments.value = res.data
				page += 10
			})
			.catch((err) => {
				console.log(err)
			})
	}
}

/**
 * 判断用户是否已收藏
 */
function isCollection() {
	//判断用户是否登录
	const token = localStorage.getItem('token')
	if (token === null || token === '' || token === undefined) {
		//未登录
		// toast.warning('请先登录')
		return
	} else {
		const playgoerId = localStorage.getItem('playgoerId')
		if (flag.value === 'audio') {
			audioIsCollection(audioId.value, playgoerId).then((res) => {
				collectionFlag.value = res.data
			})
		} else if (flag.value === 'video') {
			videoIsCollection(videoId.value, playgoerId).then((res) => {
				collectionFlag.value = res.data
			})
		}
	}
}

function checkLogin() {
	//判断用户是否登录
	const token = localStorage.getItem('token')
	if (token === null || token === '' || token === undefined) {
		//未登录
		// toast.warning('请先登录')
		return
	} else {
		loginFlag.value = true
	}
}

//下载
const downloadVideo = (videoId: any, downloadUrl: any) => {
	if (downloadUrl === null) {
		toast.warning('暂无下载链接')
		return
	}
	addVideoDownloadNum(videoId).then((res) => {
		if (res) {
			window.location.href = downloadUrl
		}
	})
}
const downloadAudio = (audioId: any, downloadUrl: any) => {
	if (downloadUrl === null) {
		toast.warning('暂无下载链接')
		return
	}
	addAudioDownloadNum(audioId).then((res) => {
		if (res) {
			console.log(downloadUrl)
			window.location.href = downloadUrl
		}
	})
}
const commentClick = (playgoerId: any) => {
	router.push({ path: '/user', query: { id: playgoerId } })
}

onMounted(() => {})

onUnmounted(() => {
	if (playerInstance.value) {
		playerInstance.value.destroy()
		playerInstance.value = null
	}
})

getOperaDetails(Route)
getComment(page.value)
isCollection()
checkLogin()
</script>

<style scoped>
.opera-container::after {
	content: '';
	background: url('~/assets/images/opera-bg.jpg') no-repeat center center fixed;
	background-size: cover;
	opacity: 0.5;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	position: absolute;
	z-index: -1;
}
.opera-container {
	width: 100vw;
	min-height: 100vh;
	position: relative;
	justify-content: center;
	align-items: center;
}
.comment-section-title {
	font-size: 24px; /* 设置字体大小 */
	color: #333; /* 设置字体颜色 */
	text-align: center; /* 设置文本居中 */
	padding: 20px 0; /* 设置上下边距 */
	border-bottom: 1px solid #ddd; /* 添加下边框 */
}
.el-descriptions {
	margin-top: 20px;
}
.cell-item {
	display: flex;
	align-items: center;
}
.margin-top {
	margin-top: 20px;
}
.infinite-list {
	height: 300px;
	padding: 0;
	margin: 0;
	list-style: none;
}
.infinite-list .infinite-list-item {
	display: flex;
	align-items: center;
	justify-content: center;
	height: 50px;
	background: var(--el-color-primary-light-9);
	margin: 10px;
	color: var(--el-color-primary);
}
.infinite-list .infinite-list-item + .list-item {
	margin-top: 10px;
}
</style>
