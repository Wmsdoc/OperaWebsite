<template>
	<div class="ranking-container">
		<span style="font-size: 18px">请选择排行：</span>
		<el-tree-select
			v-model="value"
			:data="data"
			filterable
			accordion
			style="transform: scale(1.2); margin-bottom: 20px"
			@current-change="onChange"
		/>
		<el-empty
			v-if="noDataFlag"
			description="暂无数据"
			style="font-size: 18px; color: #333; text-align: center"
		/>
		<el-card v-if="!noDataFlag" style="width: 70%">
			<template #header>
				<div class="card-header">
					<span>排行</span>
				</div>
			</template>
			<el-table
				v-if="videoFlag"
				ref="multipleTableRef"
				:data="operaVideoList"
				style="width: 100%"
			>
				<el-table-column property="filename" label="文件名" />
				<el-table-column property="downloadNum" label="下载量" width="120" />
				<el-table-column
					v-if="commentFlag"
					property="commentNum"
					label="评论量"
					width="100"
				/>
				<el-table-column property="createdAt" label="上传时间" width="400" />
				<el-table-column fixed="right" label="操作" width="300">
					<template #default="scope">
						<el-popover
							:width="300"
							popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;"
						>
							<template #reference>
								<el-button
									link
									type="primary"
									size="small"
									@mouseenter="enterVideo(scope.row.videoId)"
									@click="handleClickVideo(scope.row.videoId)"
									>详情</el-button
								>
							</template>
							<template #default>
								<div
									class="demo-rich-conent"
									style="display: flex; gap: 16px; flex-direction: column"
								>
									<el-avatar
										:size="60"
										:src="videoDetails.playgoerAvatar"
										style="margin-bottom: 8px"
									/>
									<div>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											发布者：{{ videoDetails.playgoerName }}
										</p>
										<p
											class="demo-rich-content__mention"
											style="
												margin: 0;
												font-size: 14px;
												color: var(--el-color-info);
											"
										>
											签名：{{ videoDetails.playgoerInfo }}
										</p>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											音频：{{ videoDetails.typeName }}--{{
												videoDetails.filename
											}}
										</p>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											简介：{{ videoDetails.videoInfo }}
										</p>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											下载量：{{ videoDetails.downloadNum }}
										</p>
									</div>

									<p class="demo-rich-content__desc" style="margin: 0">
										发布时间：{{ videoDetails.createdAt }}
									</p>
								</div>
							</template>
						</el-popover>

						<el-button
							link
							type="primary"
							size="small"
							@click="downloadVideo(scope.row.videoId, scope.row.downloadUrl)"
							>下载</el-button
						>
					</template>
				</el-table-column>
			</el-table>
			<el-table
				v-if="audioFlag"
				ref="multipleTableRef"
				:data="operaAudioList"
				style="width: 100%; box-sizing: border-box"
			>
				<el-table-column property="filename" label="文件名" />
				<el-table-column property="downloadNum" label="下载量" width="100" />
				<el-table-column
					v-if="commentFlag"
					property="commentNum"
					label="评论量"
					width="100"
				/>
				<el-table-column property="createdAt" label="上传时间" width="400" />
				<el-table-column fixed="right" label="操作" width="200">
					<template #default="scope">
						<el-popover
							:width="300"
							popper-style="box-shadow: rgb(14 18 22 / 35%) 0px 10px 38px -10px, rgb(14 18 22 / 20%) 0px 10px 20px -15px; padding: 20px;"
						>
							<template #reference>
								<el-button
									link
									type="primary"
									size="small"
									@mouseenter="enterAudio(scope.row.audioId)"
									@click="handleClickAudio(scope.row.audioId)"
									>详情</el-button
								>
							</template>
							<template #default>
								<div
									class="demo-rich-conent"
									style="display: flex; gap: 16px; flex-direction: column"
								>
									<el-avatar
										:size="60"
										:src="audioDetails.playgoerAvatar"
										style="margin-bottom: 8px"
									/>
									<div>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											发布者：{{ audioDetails.playgoerName }}
										</p>
										<p
											class="demo-rich-content__mention"
											style="
												margin: 0;
												font-size: 14px;
												color: var(--el-color-info);
											"
										>
											签名：{{ audioDetails.playgoerInfo }}
										</p>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											音频：{{ audioDetails.typeName }}--{{
												audioDetails.filename
											}}
										</p>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											简介：{{ audioDetails.audioInfo }}
										</p>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											下载量：{{ audioDetails.downloadNum }}
										</p>
									</div>

									<p class="demo-rich-content__desc" style="margin: 0">
										发布时间：{{ audioDetails.createdAt }}
									</p>
								</div>
							</template>
						</el-popover>
						<el-button
							link
							type="primary"
							size="small"
							@click="downloadAudio(scope.row.audioId, scope.row.downloadUrl)"
							>下载</el-button
						>
					</template>
				</el-table-column>
			</el-table>
		</el-card>
	</div>
</template>
<script setup lang="ts">
import router from '~/plugins/router'
import { OperaAudioRankVO, OperaAudioVO } from '~/api/types/audio'
import { OperaVideoRankVO, OperaVideoVO } from '~/api/types/video'

const noDataFlag = ref(true)
const audioFlag = ref(false)
const videoFlag = ref(false)
const commentFlag = ref(false)
const value = ref()

const data = [
	{
		value: 'audio',
		label: '戏曲音频',
		children: [
			{
				value: 'download',
				label: '下载榜',
				children: [
					{
						value: 'audio-download-week',
						label: '音频-下载-周榜',
					},
					{
						value: 'audio-download-month',
						label: '音频-下载-月榜',
					},
					{
						value: 'audio-download-year',
						label: '音频-下载-年榜',
					},
					{
						value: 'audio-download-all',
						label: '音频-下载-总榜',
					},
				],
			},
			{
				value: 'comment',
				label: '评论榜',
				children: [
					{
						value: 'audio-comment-week',
						label: '音频-评论-周榜',
					},
					{
						value: 'audio-comment-month',
						label: '音频-评论-月榜',
					},
					{
						value: 'audio-comment-year',
						label: '音频-评论-年榜',
					},
					{
						value: 'audio-comment-all',
						label: '音频-评论-总榜',
					},
				],
			},
		],
	},
	{
		value: 'video',
		label: '戏曲视频',
		children: [
			{
				value: 'download',
				label: '下载榜',
				children: [
					{
						value: 'video-download-week',
						label: '视频-下载-周榜',
					},
					{
						value: 'video-download-month',
						label: '视频-下载-月榜',
					},
					{
						value: 'video-download-year',
						label: '视频-下载-年榜',
					},
					{
						value: 'video-download-all',
						label: '视频-下载-总榜',
					},
				],
			},
			{
				value: 'comment',
				label: '评论榜',
				children: [
					{
						value: 'video-comment-week',
						label: '视频-评论-周榜',
					},
					{
						value: 'video-comment-month',
						label: '视频-评论-月榜',
					},
					{
						value: 'video-comment-year',
						label: '视频-评论-年榜',
					},
					{
						value: 'video-comment-all',
						label: '视频-评论-总榜',
					},
				],
			},
		],
	},
]

const operaAudioList = ref<OperaAudioRankVO[]>()
const operaVideoList = ref<OperaVideoRankVO[]>()
//详情数据
const audioDetails = ref<OperaAudioVO>({})
const videoDetails = ref<OperaVideoVO>({})

const onChange = (value: any) => {
	noDataFlag.value = true
	audioFlag.value = false
	videoFlag.value = false
	//进行字符串分割
	let splitted: string[] = value.value.split('-')
	//取第二位位并判断
	if (splitted[1] === 'download') {
		//查询下载榜
		if (splitted[0] === 'audio') {
			reset()
			getAudioDownloadRank(splitted[2]).then((res) => {
				operaAudioList.value = res.data
				console.log(res.data)
				if (res.data.length != 0) {
					noDataFlag.value = false
					audioFlag.value = true
				}
			})
		} else if (splitted[0] === 'video') {
			reset()
			getVideoDownloadRank(splitted[2]).then((res) => {
				operaVideoList.value = res.data
				console.log(res.data)
				if (res.data.length != 0) {
					noDataFlag.value = false
					videoFlag.value = true
				}
			})
		}
	} else if (splitted[1] === 'comment') {
		//查询评论榜
		if (splitted[0] === 'audio') {
			reset()
			getAudioCommentRank(splitted[2]).then((res) => {
				operaAudioList.value = res.data
				console.log(res.data)
				if (res.data.length != 0) {
					noDataFlag.value = false
					audioFlag.value = true
					commentFlag.value = true
				}
			})
		} else if (splitted[0] === 'video') {
			reset()
			getVideoCommentRank(splitted[2]).then((res) => {
				operaVideoList.value = res.data
				console.log(res.data)
				if (res.data.length != 0) {
					noDataFlag.value = false
					videoFlag.value = true
					commentFlag.value = true
				}
			})
		}
	}
}
//数据重置
const reset = () => {
	operaAudioList.value = []
	operaVideoList.value = []
	noDataFlag.value = true
	audioFlag.value = false
	videoFlag.value = false
	commentFlag.value = false
}

//鼠标悬浮事件
const enterVideo = (videoId: number) => {
	getVideoDetails(videoId).then((res) => {
		videoDetails.value = res.data
	})
}
const enterAudio = (audioId: number) => {
	getAudioDetails(audioId).then((res) => {
		audioDetails.value = res.data
	})
}
//获取详情,页面跳转
function handleClickVideo(videoId: number) {
	router.push({
		path: '/opera',
		query: {
			id: videoId,
			type: 'video',
		},
	})
}
function handleClickAudio(audioId: number) {
	router.push({
		path: '/opera',
		query: {
			id: audioId,
			type: 'audio',
		},
	})
}
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
</script>
<style scoped>
.ranking-container::after {
	content: '';
	background: url('~/assets/images/ranking-bg.jpg') no-repeat center center;
	background-size: cover;
	opacity: 0.5;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	position: absolute;
	z-index: -1;
}
.ranking-container {
	width: 100vw;
	height: calc(100vh - 80px);
	position: relative;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}
</style>
