<template>
	<div class="classify-container">
		<div>
			<el-row
				:gutter="20"
				style="width: 100%; display: flex; align-items: center"
			>
				<el-col
					:span="24"
					style="
						display: flex;
						justify-content: space-between;
						align-items: center;
						padding: 0 20px;
					"
				>
					<el-input
						v-model="input"
						class="w-3/4 m-10"
						placeholder="请输入文件名称"
						@keyup.enter="Search"
						clearable
						style="font-size: 15px; height: 40px"
					>
						<template #append>
							<button
								class="i-material-symbols:search"
								@click="Search"
							></button>
						</template>
					</el-input>
					<div>
						<span
							class="i-mdi:tag-search-outline cursor-pointer mr-2"
							style="font-size: 15px"
							@click="drawer = true"
							>筛选</span
						>
						<span
							class="cursor-pointer"
							style="font-size: 15px"
							@click="drawer = true"
							>筛选</span
						>
					</div>
				</el-col>
			</el-row>
			<!-- <el-button style="margin-left: 16px" @click="drawer = true" class="i-mdi:tag-search-outline">
				筛选
			</el-button> -->
			<el-drawer v-model="drawer" :direction="direction">
				<template #header>
					<h4>戏曲筛选</h4>
				</template>
				<template #default>
					<div>
						《戏曲格式》
						<br />
						<!-- <el-divider /> -->
						<el-radio v-model="radioFormat" label="0" size="large"
							>全部</el-radio
						>
						<el-radio v-model="radioFormat" label="1" size="large"
							>视频</el-radio
						>
						<el-radio v-model="radioFormat" label="2" size="large"
							>音频</el-radio
						>
						<el-divider />
						《戏曲分类》
						<!-- <el-divider /> -->
						<br />
						<el-radio v-model="radioType" label="0" size="large">全部</el-radio>
						<el-radio
							v-for="item in typeList"
							:key="item.typeId"
							v-model="radioType"
							:label="item.typeId"
							size="large"
							>{{ item.typeName }}</el-radio
						>
						<el-divider />
						《发布时间》
						<br />
						<el-radio v-model="radioTime" label="0" size="large">全部</el-radio>
						<el-radio v-model="radioTime" label="1" size="large">2024</el-radio>
						<el-radio v-model="radioTime" label="2" size="large">2023</el-radio>
						<el-radio v-model="radioTime" label="7" size="large">2022</el-radio>
						<el-radio v-model="radioTime" label="3" size="large">2021</el-radio>
						<el-radio v-model="radioTime" label="4" size="large">2020</el-radio>
						<el-radio v-model="radioTime" label="5" size="large"
							>2019-2016</el-radio
						>
						<el-radio v-model="radioTime" label="6" size="large">更早</el-radio>
						<el-divider />
					</div>
				</template>
				<template #footer>
					<div style="flex: auto">
						<el-button @click="cancelClick">重置</el-button>
						<el-button type="primary" @click="confirmClick">确定</el-button>
					</div>
				</template>
			</el-drawer>
		</div>
		<el-row :gutter="100">
			<el-col :span="500">
				<el-card
					class="z-1 !border-none w-100 bg-transparent !rounded-4% <sm:w-83"
					style="width: 100%"
					:style="{ backgroundColor: 'rgba(255, 255, 255, 0.5)' }"
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
						<el-table-column
							property="filename"
							label="文件名"
							width="300"
						/>
						<el-table-column fixed="right" label="操作" width="200">
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
					class="z-1 !border-none w-100 bg-transparent !rounded-4% <sm:w-83"
					style="width: 100%"
					:style="{ backgroundColor: 'rgba(255, 255, 255, 0.5)' }"
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
						<el-table-column property="filename" label="文件名" width="300" />
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
import { OperaType } from '~/api/types/type'
import { OperaAudio, OperaAudioVO } from '~/api/types/audio'
import { OperaVideo, OperaVideoVO } from '~/api/types/video'
import router from '~/plugins/router'

const loading = ref(false)
const drawer = ref(true)
const direction = 'rtl'
//戏曲格式
const radioFormat = ref('0')
//戏曲分类
const radioType = ref('0')
//发布时间
const radioTime = ref('0')
//搜索框
const input = ref('')

//分页
const currentPageVideo = ref(0)
const pageSizeVideo = ref(5)
const totalVideo = ref(0)
const currentPageAudio = ref(0)
const pageSizeAudio = ref(5)
const totalAudio = ref(0)
const small = ref(true)
const background = ref(false)

const typeList = ref<OperaType[]>()
const operaAudioList = ref<OperaAudio[]>()
const operaVideoList = ref<OperaVideo[]>()
//详情数据
const audioDetails = ref<OperaAudioVO>({})
const videoDetails = ref<OperaVideoVO>({})

const queryParams = {
	typeId: radioType.value,
	timeFlag: radioTime.value,
	filename: '',
	pageSizeAudio: pageSizeAudio.value,
	pageNumAudio: currentPageAudio.value,
	pageSizeVideo: pageSizeVideo.value,
	pageNumVideo: currentPageVideo.value,
}

function getAllOperaType() {
	getAllType().then((res) => {
		typeList.value = res.data
	})
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

//分页
const handleSizeChangeAudio = (val: number) => {
	queryParams.pageSizeAudio = val
	getAudio(queryParams).then((res) => {
		operaAudioList.value = res.data.records
		pageSizeAudio.value = res.data.size
		currentPageAudio.value = res.data.current
	})
}
const handleSizeChangeVideo = (val: number) => {
	queryParams.pageSizeVideo = val
	getVideo(queryParams).then((res) => {
		operaVideoList.value = res.data.records
		pageSizeVideo.value = res.data.size
		currentPageVideo.value = res.data.current
	})
}
const handleCurrentChangeAudio = (val: number) => {
	queryParams.pageNumAudio = val
	getAudio(queryParams).then((res) => {
		operaAudioList.value = res.data.records
		pageSizeAudio.value = res.data.size
		currentPageAudio.value = res.data.current
	})
}
const handleCurrentChangeVideo = (val: number) => {
	queryParams.pageNumVideo = val
	getVideo(queryParams).then((res) => {
		operaVideoList.value = res.data.records
		pageSizeVideo.value = res.data.size
		currentPageVideo.value = res.data.current
	})
}

//搜索
const Search = () => {
	queryParams.filename = input.value
	confirmClick()
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

//抽屉
function cancelClick() {
	radioFormat.value = '0'
	radioType.value = '0'
	radioTime.value = '0'
	confirmClick()
	drawer.value = false
}
function confirmClick() {
	//构建查询条件
	queryParams.typeId = radioType.value
	queryParams.timeFlag = radioTime.value

	if (radioFormat.value === '0') {
		//重置分页条件
		queryParams.pageNumAudio = 0
		queryParams.pageNumVideo = 0
		getOpera(queryParams)
	} else if (radioFormat.value === '1') {
		//重置分页条件
		queryParams.pageNumVideo = 0
		getVideo(queryParams).then((res) => {
			operaVideoList.value = res.data.records
			totalVideo.value = res.data.total
			pageSizeVideo.value = res.data.size
			currentPageVideo.value = res.data.current
		})
	} else if (radioFormat.value === '2') {
		//重置分页条件
		queryParams.pageNumAudio = 0
		getAudio(queryParams).then((res) => {
			operaAudioList.value = res.data.records
			totalAudio.value = res.data.total
			pageSizeAudio.value = res.data.size
			currentPageAudio.value = res.data.current
		})
	}
	drawer.value = false
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

getOpera(queryParams)
getAllOperaType()
</script>
<style scoped>
.classify-container::after {
	content: '';
	background: url('~/assets/images/classify-bg.jpg') no-repeat center center;
	background-size: cover;
	opacity: 0.5;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	position: absolute;
	z-index: -1;
}
.classify-container {
	width: 100vw;
	height: calc(100vh - 80px);
	position: relative;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}
.demo-pagination-block + .demo-pagination-block {
	margin-top: 10px;
}
.demo-pagination-block .demonstration {
	margin-bottom: 16px;
}
</style>
