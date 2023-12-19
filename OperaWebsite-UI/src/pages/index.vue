<template>
	<div>
		<el-row>
			<el-card style="width: 900px">
				<el-carousel :interval="4000" type="card" height="200px">
					<el-carousel-item v-for="item in advertList" :key="item.advertId">
						<el-image style="width: 100%; height: 100%" :src="item.imgUrl" lazy @click="handleLink(item.linkUrl)"/>
						<span style="position: absolute; bottom: 0; left: 0"
							>{{ item.advertContent }}</span
						>
					</el-carousel-item>
				</el-carousel>
				<div class="card-header">
					<h5>网站公告</h5>
					<br />
					<el-row class="mt-4" v-for="item in noticeList">
						<el-tag class="ml-2" type="success">
							{{ item.noticeContent }} -- {{ item.noticeCreatedAt }}<br />
						</el-tag>
					</el-row>
				</div>
			</el-card>
		</el-row>
		<el-divider />
		<el-row :gutter="100">
			<el-col :span="500">
				<el-card
					class="z-1 !border-none w-100 !bg-transparent !rounded-4% <sm:w-83"
				>
					<template #header>
						<div class="card-header">
							<span>戏曲视频</span>
						</div>
					</template>
					<!-- <el-empty description="暂无数据" /> -->
					<el-table
						ref="multipleTableRef"
						:data="operaVideoList"
						style="width: 100%"
					>
						<!-- <el-table-column type="selection" width="55" /> -->
						<el-table-column property="filename" label="文件名" width="120" />
						<el-table-column fixed="right" label="操作" width="120">
							<template #default="scope">
								<!-- <el-button link type="primary" size="small" @click="handleClickVideo"
									>详情</el-button
								> -->
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
									@click="
										downloadVideo(scope.row.videoId, scope.row.downloadUrl)
									"
									size="small"
									>下载</el-button
								>
							</template>
						</el-table-column>
					</el-table>
					<el-divider />
					<div class="demo-pagination-block">
						<el-pagination
							v-model:current-page="currentPageVideo"
							v-model:page-size="pageSizeVideo"
							:page-sizes="[5, 10, 15, 20]"
							:small="small"
							:background="background"
							layout="total, sizes, prev, pager, next, jumper"
							:total="totalVideo"
							@size-change="handleSizeChangeVideo"
							@current-change="handleCurrentChangeVideo"
						/>
					</div>
				</el-card>
			</el-col>
			<el-col :span="500">
				<el-card
					class="z-1 !border-none w-100 !bg-transparent !rounded-4% <sm:w-83"
				>
					<template #header>
						<div class="card-header">
							<span>戏曲音频</span>
							<!-- <el-button class="button" text>Operation button</el-button> -->
						</div>
					</template>
					<!-- <el-empty description="暂无数据" v-if="operaAudioList == null" /> -->
					<el-table
						ref="multipleTableRef"
						:data="operaAudioList"
						style="width: 100%"
					>
						<!-- <el-table-column type="selection" width="55" /> -->
						<!-- <el-table-column label="Date" width="120">
							<template #default="scope">{{ scope.row.date }}</template>
						</el-table-column> -->
						<el-table-column property="filename" label="文件名" width="120" />
						<el-table-column fixed="right" label="操作" width="120">
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
									@click="
										downloadAudio(scope.row.audioId, scope.row.downloadUrl)
									"
									size="small"
									>下载</el-button
								>
							</template>
						</el-table-column>
					</el-table>
					<el-divider />
					<div class="demo-pagination-block">
						<el-pagination
							v-model:current-page="currentPageAudio"
							v-model:page-size="pageSizeAudio"
							:page-sizes="[5, 10, 15, 20]"
							:small="small"
							:background="background"
							layout="total, sizes, prev, pager, next, jumper"
							:total="totalAudio"
							@size-change="handleSizeChangeAudio"
							@current-change="handleCurrentChangeAudio"
						/>
					</div>
				</el-card>
			</el-col>
		</el-row>
	</div>
</template>
<script setup lang="ts">
import { OperaAudio, OperaAudioVO } from '~/api/types/audio'
import { OperaVideo, OperaVideoVO } from '~/api/types/video'
import { Notice,Advert } from '~/api/types/website'
import router from '~/plugins/router'
// import { getAudio } from '~/api/index'

const url =
	'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
const loading = ref(false)

//分页
const currentPageVideo = ref(0)
const pageSizeVideo = ref(5)
const totalVideo = ref(0)
const currentPageAudio = ref(0)
const pageSizeAudio = ref(5)
const totalAudio = ref(0)
const small = ref(true)
const background = ref(false)

const operaAudioList = ref<OperaAudio[]>()
const operaVideoList = ref<OperaVideo[]>()
//详情数据
const audioDetails = ref<OperaAudioVO>({})
const videoDetails = ref<OperaVideoVO>({})
const noticeList = ref<Notice[]>()
const advertList = ref<Advert[]>()

//首页走马灯
const carouselList = ref()

const queryParams = {
	filename: null,
	pageSizeAudio: pageSizeAudio.value,
	pageNumAudio: currentPageAudio.value,
	pageSizeVideo: pageSizeVideo.value,
	pageNumVideo: currentPageVideo.value,
}

function getOpera(queryParams: any) {
	getVideo(queryParams)
		.then((res) => {
			operaVideoList.value = res.data.records
			totalVideo.value = res.data.total
			pageSizeVideo.value = res.data.size
			currentPageVideo.value = res.data.current
		})
		.then(() => {
			getAudio(queryParams).then((res) => {
				operaAudioList.value = res.data.records
				totalAudio.value = res.data.total
				pageSizeAudio.value = res.data.size
				currentPageAudio.value = res.data.current
			})
		})
		.finally(() => {
			loading.value = false
		})
}

const handleSizeChangeAudio = (val: number) => {
	queryParams.pageSizeAudio = val
	getAudio(queryParams).then((res) => {
		operaAudioList.value = res.data.records
		pageSizeAudio.value = res.data.size
		currentPageAudio.value = res.data.current
	})
}
const handleSizeChangeVideo = (val: number) => {
	console.log(`page size: ${val}`)
	queryParams.pageSizeVideo = val
	getVideo(queryParams).then((res) => {
		operaVideoList.value = res.data.records
		pageSizeVideo.value = res.data.size
		currentPageVideo.value = res.data.current
	})
}
const handleCurrentChangeAudio = (val: number) => {
	console.log(`current page: ${val}`)
	queryParams.pageNumAudio = val
	getAudio(queryParams).then((res) => {
		operaAudioList.value = res.data.records
		pageSizeAudio.value = res.data.size
		currentPageAudio.value = res.data.current
	})
}
const handleCurrentChangeVideo = (val: number) => {
	console.log(`current page: ${val}`)
	queryParams.pageNumVideo = val
	getVideo(queryParams).then((res) => {
		operaVideoList.value = res.data.records
		pageSizeVideo.value = res.data.size
		currentPageVideo.value = res.data.current
	})
}

//鼠标悬浮事件
const enterVideo = (videoId: number) => {
	getVideoDetails(videoId).then((res) => {
		videoDetails.value = res.data
		console.log(res.data)
	})
}
const enterAudio = (audioId: number) => {
	console.log(audioId)
	getAudioDetails(audioId).then((res) => {
		audioDetails.value = res.data
		console.log(res.data)
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

//获取网站公告
function getNotice() {
	getNoticeList().then((res) => {
		console.log(res.data)
		noticeList.value = res.data.records
	})
}
//获取网站广告
function getAdvert() {
	getAdvertList().then((res) => {
		console.log(res.data)
		advertList.value = res.data.records
	})
}
const handleLink = (linkUrl:string) => {
	window.open(linkUrl, '_blank');
}

getNotice()
getAdvert()
getOpera(queryParams)
</script>

<style>
.el-carousel__item h3 {
	color: #475669;
	opacity: 0.75;
	line-height: 200px;
	margin: 0;
	text-align: center;
}

.el-carousel__item:nth-child(2n) {
	background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
	background-color: #d3dce6;
}
</style>
