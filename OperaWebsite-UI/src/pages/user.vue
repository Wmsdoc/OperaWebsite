<template>
	<div>
		<el-card class="box-card" style="width: 600px">
			<template #header>
				<div class="card-header">
					<span>个人中心</span>
				</div>
			</template>
			<el-avatar :size="100" :src="playgoer.playgoerAvatar as string" />
			<el-button v-if="flag" class="button" @click="dialogForAvatar = true" text
				>修改头像</el-button
			>
			<el-descriptions
				class="margin-top"
				:column="3"
				:size="size"
				direction="vertical"
				border
			>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">昵称</div>
					</template>
					{{ playgoer.playgoerName }}
				</el-descriptions-item>

				<el-descriptions-item>
					<template #label>
						<div class="cell-item">签名</div>
					</template>
					{{ playgoer.playgoerInfo }}
				</el-descriptions-item>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">性别</div>
					</template>

					<el-tag size="small"
						><div v-if="playgoer.playgoerSex == '0'">
							<span>保密</span>
						</div>
						<div v-if="playgoer.playgoerSex == '1'">
							<span>男</span>
						</div>
						<div v-if="playgoer.playgoerSex == '2'">
							<span>女</span>
						</div>
					</el-tag>
				</el-descriptions-item>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">生日</div>
					</template>
					{{ playgoer.playgoerBirthday }}
				</el-descriptions-item>
				<el-descriptions-item>
					<template #label>
						<div class="cell-item">注册时间</div>
					</template>
					{{ playgoer.createdAt }}
				</el-descriptions-item>
			</el-descriptions>
			<el-divider></el-divider>
			<div class="demo-collapse">
				<el-collapse v-model="activeName" accordion @change="handleChange">
					<el-collapse-item v-if="flag" title="我的收藏" name="1">
						<el-collapse
							v-model="activeCollect"
							accordion
							@change="handleCollectChange"
						>
							<el-collapse-item title="收藏的音频" name="audio-collect">
								<div>
									<el-table
										:data="collectList"
										style="width: 100%"
										max-height="250"
									>
										<el-table-column
											prop="filename"
											label="音频名称"
											width="120"
										/>
										<el-table-column prop="typeName" label="类型" width="80" />
										<el-table-column
											prop="createdAt"
											label="收藏时间"
											width="200"
										/>
										<el-table-column
											fixed="right"
											label="Operations"
											width="120"
										>
											<template #default="scope">
												<el-button
													link
													type="primary"
													size="small"
													@click="delAudioCollection(scope.row.audioId)"
												>
													取消收藏
												</el-button>
											</template>
										</el-table-column>
									</el-table>
									<el-button
										v-if="nextFlag"
										class="mt-4"
										style="width: 100%"
										@click="onAddAudioCollect"
										>更多</el-button
									>
								</div>
							</el-collapse-item>
							<el-collapse-item title="收藏的视频" name="video-collect">
								<div>
									<el-table
										:data="collectList"
										style="width: 100%"
										max-height="250"
									>
										<el-table-column
											prop="filename"
											label="视频名称"
											width="120"
										/>
										<el-table-column prop="typeName" label="类型" width="80" />
										<el-table-column
											prop="createdAt"
											label="收藏时间"
											width="200"
										/>
										<el-table-column
											fixed="right"
											label="Operations"
											width="120"
										>
											<template #default="scope">
												<el-button
													link
													type="primary"
													size="small"
													@click="delVideoCollection(scope.row.videoId)"
												>
													取消收藏
												</el-button>
											</template>
										</el-table-column>
									</el-table>
									<el-button
										v-if="nextFlag"
										class="mt-4"
										style="width: 100%"
										@click="onAddVideoCollect"
										>更多</el-button
									>
								</div>
							</el-collapse-item>
						</el-collapse>
					</el-collapse-item>
					<el-collapse-item title="我的评论" name="2">
						<el-collapse
							v-model="activeCollect"
							accordion
							@change="handleCommentChange"
						>
							<el-collapse-item title="音频评论" name="audio-comment">
								<div>
									<el-table
										:data="commentList"
										style="width: 100%"
										max-height="250"
									>
										<el-table-column
											prop="filename"
											label="音频名称"
											width="120"
										/>
										<el-table-column prop="typeName" label="类型" width="80" />
										<el-table-column
											prop="commentInfo"
											label="评论内容"
											width="200"
										/>
										<el-table-column
											prop="updatedAt"
											label="评论时间"
											width="200"
										/>
										<el-table-column
											v-if="flag"
											fixed="right"
											label="Operations"
											width="120"
										>
											<template #default="scope">
												<el-button
													link
													type="primary"
													size="small"
													@click="
														updateAudioComment(
															scope.row.commentId,
															scope.row.commentInfo,
														)
													"
												>
													修改
												</el-button>
												<el-button
													link
													type="primary"
													size="small"
													@click="delAudioComment(scope.row.commentId)"
												>
													删除
												</el-button>
											</template>
										</el-table-column>
									</el-table>
									<el-button
										v-if="nextFlag"
										class="mt-4"
										style="width: 100%"
										@click="onAddAudioComment"
										>更多</el-button
									>
								</div>
							</el-collapse-item>
							<el-collapse-item title="视频评论" name="video-comment">
								<div>
									<el-table
										:data="commentList"
										style="width: 100%"
										max-height="250"
									>
										<el-table-column
											prop="filename"
											label="视频名称"
											width="120"
										/>
										<el-table-column prop="typeName" label="类型" width="80" />
										<el-table-column
											prop="commentInfo"
											label="评论内容"
											width="200"
										/>
										<el-table-column
											prop="updatedAt"
											label="评论时间"
											width="200"
										/>
										<el-table-column
											v-if="flag"
											fixed="right"
											label="Operations"
											width="120"
										>
											<template #default="scope">
												<el-button
													link
													type="primary"
													size="small"
													@click="
														updateVideoComment(
															scope.row.commentId,
															scope.row.commentInfo,
														)
													"
												>
													修改
												</el-button>
												<el-button
													link
													type="primary"
													size="small"
													@click="delVideoComment(scope.row.commentId)"
												>
													删除
												</el-button>
											</template>
										</el-table-column>
									</el-table>
									<el-button
										v-if="nextFlag"
										class="mt-4"
										style="width: 100%"
										@click="onAddVideoComment"
										>更多</el-button
									>
								</div>
							</el-collapse-item>
						</el-collapse>
					</el-collapse-item>
					<el-collapse-item v-if="flag" title="我的上传" name="3">
						<el-collapse
							v-model="activeCollect"
							accordion
							@change="handleUploadChange"
						>
							<el-collapse-item title="音频上传" name="audio-upload">
								<div>
									<el-table
										:data="uploadList"
										style="width: 100%"
										max-height="250"
									>
										<el-table-column
											prop="isExamine"
											label="审核状态"
											width="100"
										>
											<template #default="scope">
												<el-tag class="ml-2" v-if="scope.row.isExamine === 1"
													>已通过</el-tag
												>
												<el-tag
													class="ml-2"
													type="warning"
													v-if="scope.row.isExamine === 0"
													>审核中</el-tag
												>
											</template>
										</el-table-column>
										<el-table-column
											prop="filename"
											label="音频名称"
											width="120"
										/>
										<el-table-column prop="typeName" label="类型" width="80" />
										<el-table-column
											prop="audioInfo"
											label="音频简介"
											width="200"
										/>
										<el-table-column
											prop="createdAt"
											label="上传时间"
											width="200"
										/>
										<el-table-column
											fixed="right"
											label="Operations"
											width="120"
										>
											<template #default="scope">
												<el-button
													link
													type="primary"
													size="small"
													@click="updateAudioDialog(scope.row)"
												>
													修改
												</el-button>
												<el-button
													link
													type="primary"
													size="small"
													@click="delAudio(scope.row.audioId)"
												>
													删除
												</el-button>
											</template>
										</el-table-column>
									</el-table>
									<el-button
										v-if="nextFlag"
										class="mt-4"
										style="width: 100%"
										@click="onAddAudio"
										>更多</el-button
									>
								</div>
							</el-collapse-item>
							<el-collapse-item title="视频上传" name="video-upload">
								<div>
									<el-table
										:data="uploadList"
										style="width: 100%"
										max-height="250"
									>
										<el-table-column
											prop="isExamine"
											label="审核状态"
											width="100"
										>
											<template #default="scope">
												<el-tag class="ml-2" v-if="scope.row.isExamine === 1"
													>已通过</el-tag
												>
												<el-tag
													class="ml-2"
													type="warning"
													v-if="scope.row.isExamine === 0"
													>审核中</el-tag
												>
											</template>
										</el-table-column>
										<el-table-column
											prop="filename"
											label="视频名称"
											width="120"
										/>
										<el-table-column prop="typeName" label="类型" width="80" />
										<el-table-column
											prop="videoInfo"
											label="视频简介"
											width="200"
										/>
										<el-table-column
											prop="createdAt"
											label="上传时间"
											width="200"
										/>
										<el-table-column
											fixed="right"
											label="Operations"
											width="120"
										>
											<template #default="scope">
												<el-button
													link
													type="primary"
													size="small"
													@click="updateVideoDialog(scope.row)"
												>
													修改
												</el-button>
												<el-button
													link
													type="primary"
													size="small"
													@click="delVideo(scope.row.videoId)"
												>
													删除
												</el-button>
											</template>
										</el-table-column>
									</el-table>
									<el-button
										v-if="nextFlag"
										class="mt-4"
										style="width: 100%"
										@click="onAddVideo"
										>更多</el-button
									>
								</div>
							</el-collapse-item>
						</el-collapse>
					</el-collapse-item>
					<el-collapse-item v-if="flag" title="我的活动" name="4">
						<div>
							<el-table
								:data="activityList"
								style="width: 100%"
								max-height="250"
							>
								<el-table-column
									prop="activityName"
									label="活动名称"
									width="120"
								/>
								<el-table-column
									prop="startTime"
									label="开始时间"
									width="200"
								/>
								<el-table-column
									prop="activityAddress"
									label="活动地址"
									width="200"
								/>
								<el-table-column fixed="right" label="Operations" width="120">
									<template #default="scope">
										<el-button
											link
											type="primary"
											size="small"
											@click.prevent="delActivity(scope.row.activityId)"
										>
											取消报名
										</el-button>
									</template>
								</el-table-column>
							</el-table>
							<el-button
								v-if="nextFlag"
								class="mt-4"
								style="width: 100%"
								@click="onAddActivity"
								>Add Item</el-button
							>
						</div>
					</el-collapse-item>
				</el-collapse>
			</div>
			<el-divider></el-divider>
			<el-button v-if="flag" class="button" @click="open" text>编辑 </el-button>
			<ElButton v-if="flag" @click="doLogout">退出登录</ElButton>
		</el-card>

		<!-- 编辑个人信息弹出框 -->
		<el-dialog
			v-model="dialogVisible"
			title="编辑个人信息"
			width="30%"
			:before-close="handleClose"
		>
			<el-form :model="updateForm" label-width="80px">
				<el-form-item label="昵称">
					<el-input v-model="updateForm.playgoerName" type="text" />
				</el-form-item>
				<el-form-item label="签名">
					<el-input
						v-model="updateForm.playgoerInfo"
						:rows="2"
						autosize
						maxlength="200"
						show-word-limit
						type="textarea"
					/>
				</el-form-item>
				<el-form-item label="性别">
					<el-radio-group v-model="updateForm.playgoerSex">
						<el-radio :label="0">保密</el-radio>
						<el-radio :label="1">男</el-radio>
						<el-radio :label="2">女</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="生日">
					<el-date-picker
						v-model="updateForm.playgoerBirthday"
						type="date"
						placeholder="请选择日期"
						value-format="YYYY-MM-DD"
					/>
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="dialogVisible = false">取消</el-button>
					<el-button type="primary" @click="handleUpdate(updateForm)">
						提交
					</el-button>
				</span>
			</template>
		</el-dialog>

		<el-dialog v-model="dialogForAvatar" title="更新头像" width="30%">
			<el-upload
				class="upload-demo"
				drag
				ref="upload"
				:http-request="toUpload"
				:limit="1"
				element-loading-text="正在上传"
			>
				<div class="i-mdi:cloud-upload-outline el-icon--upload"></div>
				<div class="el-upload__text">
					Drop file here or <em>click to upload</em>
				</div>

				<template #tip>
					<div class="el-upload__tip">
						jpg/png files with a size less than 500kb
					</div>
				</template>
			</el-upload>
		</el-dialog>

		<el-dialog
			v-model="dialogForAudio"
			title="编辑戏曲音频"
			width="30%"
			:before-close="handleClose"
		>
			<el-form :model="updateAudioForm" label-width="80px">
				<el-form-item label="文件名">
					<el-input v-model="updateAudioForm.filename" type="text" />
				</el-form-item>
				<el-form-item label="音频简介">
					<el-input
						v-model="updateAudioForm.audioInfo"
						:rows="2"
						autosize
						maxlength="200"
						show-word-limit
						type="textarea"
					/>
				</el-form-item>
				<el-form-item label="音频类型">
					<el-select
						v-model="updateAudioForm.typeName"
						class="m-2"
						placeholder="Select"
						@focus="getAllOperaType"
						@change="updateAudioForm.typeId = $event"
					>
						<el-option
							v-for="item in options"
							:key="item.typeId"
							:label="item.typeName"
							:value="(item.typeId as string)"
						/>
					</el-select>
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="dialogForAudio = false">取消</el-button>
					<el-button type="primary" @click="handleAudioUpdate(updateAudioForm)">
						提交
					</el-button>
				</span>
			</template>
		</el-dialog>
		<el-dialog
			v-model="dialogForVideo"
			title="编辑戏曲视频"
			width="30%"
			:before-close="handleClose"
		>
			<el-form :model="updateVideoForm" label-width="80px">
				<el-form-item label="文件名">
					<el-input v-model="updateVideoForm.filename" type="text" />
				</el-form-item>
				<el-form-item label="视频简介">
					<el-input
						v-model="updateVideoForm.videoInfo"
						:rows="2"
						autosize
						maxlength="200"
						show-word-limit
						type="textarea"
					/>
				</el-form-item>
				<el-form-item label="视频类型">
					<el-select
						v-model="updateVideoForm.typeName"
						class="m-2"
						placeholder="Select"
						@focus="getAllOperaType"
						@change="updateVideoForm.typeId = $event"
					>
						<el-option
							v-for="item in options"
							:key="item.typeId"
							:label="item.typeName"
							:value="item.typeId as string"
						/>
					</el-select>
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button @click="dialogForVideo = false">取消</el-button>
					<el-button type="primary" @click="handleVideoUpdate(updateVideoForm)">
						提交
					</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>
<script setup lang="ts">
import { logout } from '~/api/user'
import router from '~/plugins/router'
import { Playgoer } from '~/api/types/user'
import { UploadRequestOptions } from 'element-plus'
import { OperaType } from '~/api/types/type'

const size = 'large'

const pageNum = ref(0)
const pageSize = ref(5)
const nextFlag = ref(false)

// 判断是自己的用户界面还是他人的用户界面
const flag = ref(false)
let playgoerId = localStorage.getItem('playgoerId')
const playgoer = ref<Playgoer>({})
const updateForm = reactive({
	playgoerId: playgoerId,
	playgoerName: '',
	playgoerInfo: '',
	playgoerSex: '',
	playgoerBirthday: '',
})
const options = ref<OperaType[]>()
const updateAudioForm = reactive({
	audioId: '',
	audioInfo: '',
	filename: '',
	typeName: '',
	typeId: '',
})
const updateVideoForm = reactive({
	videoId: '',
	videoInfo: '',
	filename: '',
	typeName: '',
	typeId: '',
})

const Route = useRoute() //获取到值

const dialogVisible = ref(false)
const dialogForAvatar = ref(false)
const dialogForAudio = ref(false)
const dialogForVideo = ref(false)

const collectList = ref([])
const commentList = ref([])
const uploadList = ref([])
const activityList = ref([])

//折叠面板
const activeName = ref('0')
const activeCollect = ref('0')
const handleChange = (val: any) => {
	console.log(val)
	if (val === '1') {
		//查询用户收藏 handleCollectChange
	} else if (val === '2') {
		//查询用户评论 handleCommentChange
	} else if (val === '3') {
		//查询我的上传 handleUploadChange
	} else if (val === '4') {
		//还原分页信息
		pageNum.value = 1
		pageSize.value = 5
		//查询用户活动
		getPlaygoerActivity(pageNum.value, pageSize.value).then((res) => {
			activityList.value = res.data.records
			if (res.data.total <= pageNum.value * pageSize.value) {
				nextFlag.value = false
			} else {
				nextFlag.value = true
			}
		})
	}
}
const handleCollectChange = (val: any) => {
	//还原分页信息
	pageNum.value = 1
	pageSize.value = 5
	collectList.value = []
	if (val === 'audio-collect') {
		//查询用户音频收藏
		getPlaygoerAudioCollect(pageNum.value, pageSize.value).then((res) => {
			collectList.value = res.data.records
			if (res.data.total <= pageNum.value * pageSize.value) {
				nextFlag.value = false
			} else {
				nextFlag.value = true
			}
		})
	} else if (val === 'video-collect') {
		//查询用户视频收藏
		getPlaygoerVideoCollect(pageNum.value, pageSize.value).then((res) => {
			collectList.value = res.data.records
			if (res.data.total <= pageNum.value * pageSize.value) {
				nextFlag.value = false
			} else {
				nextFlag.value = true
			}
		})
	}
}
const handleCommentChange = (val: any) => {
	//还原分页信息
	pageNum.value = 1
	pageSize.value = 5
	commentList.value = []
	if (val === 'audio-comment') {
		//查询用户音频评论
		getPlaygoerAudioComment(playgoerId, pageNum.value, pageSize.value).then(
			(res) => {
				commentList.value = res.data.records
				if (res.data.total <= pageNum.value * pageSize.value) {
					nextFlag.value = false
				} else {
					nextFlag.value = true
				}
			},
		)
	} else if (val === 'video-comment') {
		//查询用户视频评论
		getPlaygoerVideoComment(playgoerId, pageNum.value, pageSize.value).then(
			(res) => {
				commentList.value = res.data.records
				if (res.data.total <= pageNum.value * pageSize.value) {
					nextFlag.value = false
				} else {
					nextFlag.value = true
				}
			},
		)
	}
}
const handleUploadChange = (val: any) => {
	//还原分页信息
	pageNum.value = 1
	pageSize.value = 5
	uploadList.value = []
	if (val === 'audio-upload') {
		getPlaygoerAudioUpload(pageNum.value, pageSize.value).then((res) => {
			console.log(res)

			uploadList.value = res.data.records
			if (res.data.total <= pageNum.value * pageSize.value) {
				nextFlag.value = false
			} else {
				nextFlag.value = true
			}
		})
	} else if (val === 'video-upload') {
		getPlaygoerVideoUpload(pageNum.value, pageSize.value).then((res) => {
			uploadList.value = res.data.records
			if (res.data.total <= pageNum.value * pageSize.value) {
				nextFlag.value = false
			} else {
				nextFlag.value = true
			}
		})
	}
}

//分页查询用户收藏的音频
const onAddAudioCollect = () => {
	pageNum.value++
	getPlaygoerAudioCollect(pageNum.value, pageSize.value).then((res) => {
		collectList.value = collectList.value.concat(res.data.records)
		if (res.data.total <= pageNum.value * pageSize.value) {
			nextFlag.value = false
		} else {
			nextFlag.value = true
		}
	})
}
const onAddVideoCollect = () => {
	pageNum.value++
	getPlaygoerVideoCollect(pageNum.value, pageSize.value).then((res) => {
		collectList.value = collectList.value.concat(res.data.records)
		if (res.data.total <= pageNum.value * pageSize.value) {
			nextFlag.value = false
		} else {
			nextFlag.value = true
		}
	})
}
const onAddActivity = () => {
	pageNum.value++
	getPlaygoerActivity(pageNum.value, pageSize.value).then((res) => {
		activityList.value = activityList.value.concat(res.data.records)
		if (res.data.total <= pageNum.value * pageSize.value) {
			nextFlag.value = false
		} else {
			nextFlag.value = true
		}
	})
}
const onAddAudioComment = () => {
	pageNum.value++
	getPlaygoerAudioComment(playgoerId, pageNum.value, pageSize.value).then(
		(res) => {
			commentList.value = commentList.value.concat(res.data.records)
			if (res.data.total <= pageNum.value * pageSize.value) {
				nextFlag.value = false
			} else {
				nextFlag.value = true
			}
		},
	)
}
const onAddVideoComment = () => {
	pageNum.value++
	getPlaygoerVideoComment(playgoerId, pageNum.value, pageSize.value).then(
		(res) => {
			commentList.value = commentList.value.concat(res.data.records)
			if (res.data.total <= pageNum.value * pageSize.value) {
				nextFlag.value = false
			} else {
				nextFlag.value = true
			}
		},
	)
}
const onAddAudio = () => {
	pageNum.value++
	getPlaygoerAudioUpload(pageNum.value, pageSize.value).then((res) => {
		uploadList.value = uploadList.value.concat(res.data.records)
		if (res.data.total <= pageNum.value * pageSize.value) {
			nextFlag.value = false
		} else {
			nextFlag.value = true
		}
	})
}
const onAddVideo = () => {
	pageNum.value++
	getPlaygoerVideoUpload(pageNum.value, pageSize.value).then((res) => {
		uploadList.value = uploadList.value.concat(res.data.records)
		if (res.data.total <= pageNum.value * pageSize.value) {
			nextFlag.value = false
		} else {
			nextFlag.value = true
		}
	})
}

//取消收藏
const delAudioCollection = (audioId: any) => {
	deleteAudioCollection(audioId, playgoerId).then((res) => {
		toast.success('取消收藏成功')
		//还原分页信息
		pageNum.value = 1
		pageSize.value = 5
		getPlaygoerAudioCollect(pageNum.value, pageSize.value).then((res) => {
			collectList.value = res.data.records
			if (res.data.total <= pageNum.value * pageSize.value) {
				nextFlag.value = false
			} else {
				nextFlag.value = true
			}
		})
	})
}
const delVideoCollection = (videoId: any) => {
	deleteVideoCollection(videoId, playgoerId).then((res) => {
		toast.success('取消收藏成功')
		//还原分页信息
		pageNum.value = 1
		pageSize.value = 5
		getPlaygoerVideoCollect(pageNum.value, pageSize.value).then((res) => {
			collectList.value = res.data.records
			if (res.data.total <= pageNum.value * pageSize.value) {
				nextFlag.value = false
			} else {
				nextFlag.value = true
			}
		})
	})
}

//修改音频评论
const updateAudioComment = (commentId: any, commentInfo: any) => {
	ElMessageBox.prompt('请输入评论内容', '提示', {
		inputValue: commentInfo,
		inputPattern: /\S/,
		inputErrorMessage: '评论内容不能为空',
	}).then(({ value }) => {
		let formData = new FormData()
		formData.append('commentId', commentId)
		formData.append('commentInfo', value)
		updateAudioCommentInfo(formData).then((res) => {
			toast.success('修改评论成功')
			//还原分页信息
			pageNum.value = 1
			pageSize.value = 5
			getPlaygoerAudioComment(playgoerId, pageNum.value, pageSize.value).then(
				(res) => {
					commentList.value = res.data.records
					if (res.data.total <= pageNum.value * pageSize.value) {
						nextFlag.value = false
					} else {
						nextFlag.value = true
					}
				},
			)
		})
	})
}
//修改视频评论
const updateVideoComment = (commentId: any, commentInfo: any) => {
	ElMessageBox.prompt('请输入评论内容', '提示', {
		inputValue: commentInfo,
		inputPattern: /\S/,
		inputErrorMessage: '评论内容不能为空',
	}).then(({ value }) => {
		let formData = new FormData()
		formData.append('commentId', commentId)
		formData.append('commentInfo', value)
		updateVideoCommentInfo(formData).then((res) => {
			toast.success('修改评论成功')
			//还原分页信息
			pageNum.value = 1
			pageSize.value = 5
			getPlaygoerVideoComment(playgoerId, pageNum.value, pageSize.value).then(
				(res) => {
					commentList.value = res.data.records
					if (res.data.total <= pageNum.value * pageSize.value) {
						nextFlag.value = false
					} else {
						nextFlag.value = true
					}
				},
			)
		})
	})
}
//修改音频上传
const updateAudioDialog = (row: any) => {
	updateAudioForm.audioId = row.audioId
	updateAudioForm.audioInfo = row.audioInfo
	updateAudioForm.filename = row.filename
	updateAudioForm.typeName = row.typeName
	dialogForAudio.value = true
}
const updateVideoDialog = (row: any) => {
	updateVideoForm.videoId = row.videoId
	updateVideoForm.videoInfo = row.videoInfo
	updateVideoForm.filename = row.filename
	updateVideoForm.typeName = row.typeName
	dialogForVideo.value = true
}

//删除音频评论
const delAudioComment = (commentId: any) => {
	deleteAudioComment(commentId).then((res) => {
		toast.success('删除评论成功')
		//还原分页信息
		pageNum.value = 1
		pageSize.value = 5
		getPlaygoerAudioComment(playgoerId, pageNum.value, pageSize.value).then(
			(res) => {
				commentList.value = res.data.records
				if (res.data.total <= pageNum.value * pageSize.value) {
					nextFlag.value = false
				} else {
					nextFlag.value = true
				}
			},
		)
	})
}
//删除视频评论
const delVideoComment = (commentId: any) => {
	deleteVideoComment(commentId).then((res) => {
		toast.success('删除评论成功')
		//还原分页信息
		pageNum.value = 1
		pageSize.value = 5
		getPlaygoerVideoComment(playgoerId, pageNum.value, pageSize.value).then(
			(res) => {
				commentList.value = res.data.records
				if (res.data.total <= pageNum.value * pageSize.value) {
					nextFlag.value = false
				} else {
					nextFlag.value = true
				}
			},
		)
	})
}
//删除音频
const delAudio = (audioId: any) => {
	deleteAudioUpload(audioId).then((res) => {
		if (res.data === true) {
			toast.success('删除成功')
			handleUploadChange('audio-upload')
		}
	})
}
//删除视频
const delVideo = (videoId: any) => {
	deleteVideoUpload(videoId).then((res) => {
		if (res.data === true) {
			toast.success('删除成功')
			handleUploadChange('video-upload')
		}
	})
}
//取消活动
const delActivity = (activityId: any) => {
	deletePlaygoerActivity(activityId, playgoerId).then((res) => {
		toast.success('取消报名成功')
		//还原分页信息
		pageNum.value = 1
		pageSize.value = 5
		getPlaygoerActivity(pageNum.value, pageSize.value).then((res) => {
			activityList.value = res.data.records
			if (res.data.total <= pageNum.value * pageSize.value) {
				nextFlag.value = false
			} else {
				nextFlag.value = true
			}
		})
	})
}

function getAllOperaType() {
	if (options.value !== undefined) return

	getAllType().then((res) => {
		options.value = res.data
	})
}

function getPlaygoerById() {
	if (
		Route.query.id === null ||
		Route.query.id === undefined ||
		Route.query.id === ''
	) {
		flag.value = true
	} else {
		if (Route.query.id === playgoerId) {
			flag.value = true
		} else {
			playgoerId = Route.query.id as string
			flag.value = false
		}
	}
	if (playgoerId === null || playgoerId === undefined || playgoerId === '') {
		flag.value = false
		return
	}
	getPlaygoer(playgoerId).then((res) => {
		playgoer.value = res.data
		console.log(playgoer.value)
	})
}

function doLogout() {
	logout().then((res) => {
		console.log(res)
		if (res.data == null) {
			toast.error('退出登录失败')
		} else {
			localStorage.removeItem('token')
			localStorage.removeItem('playgoerId')
			toast.success('退出登录成功')
			router.replace({ path: '/' })
		}
	})
}

function handleUpdate(updateForm: any) {
	dialogVisible.value = false
	const formData = new FormData()
	formData.append('playgoerId', updateForm.playgoerId)
	formData.append('playgoerName', updateForm.playgoerName)
	formData.append('playgoerInfo', updateForm.playgoerInfo)
	formData.append('playgoerSex', updateForm.playgoerSex)
	formData.append('playgoerBirthday', updateForm.playgoerBirthday)
	updatePlaygoer(formData).then((res) => {
		toast.success('修改成功')
		getPlaygoer(playgoerId).then((res) => {
			playgoer.value = res.data
		})
	})
}

function handleAudioUpdate(updateAudioForm: any) {
	console.log(updateAudioForm)
	let formData = new FormData()
	formData.append('audioId', updateAudioForm.audioId)
	formData.append('audioInfo', updateAudioForm.audioInfo)
	formData.append('filename', updateAudioForm.filename)
	formData.append('typeId', updateAudioForm.typeId)
	updateAudioUploadInfo(formData).then((res) => {
		if (res.data === true) {
			toast.success('修改成功，请等待审核')
			handleUploadChange('audio-upload')
			dialogForAudio.value = false
		} else {
			toast.error('修改失败')
		}
	})
}
function handleVideoUpdate(updateVideoForm: any) {
	let formData = new FormData()
	formData.append('videoId', updateVideoForm.videoId)
	formData.append('videoInfo', updateVideoForm.videoInfo)
	formData.append('filename', updateVideoForm.filename)
	formData.append('typeId', updateVideoForm.typeId)
	updateVideoUploadInfo(formData).then((res) => {
		if (res.data === true) {
			toast.success('修改成功，请等待审核')
			handleUploadChange('video-upload')
			dialogForVideo.value = false
		} else {
			toast.error('修改失败')
		}
	})
}

const toUpload = (param: UploadRequestOptions) => {
	// debugger
	const file = param.file
	const formData = new FormData()
	formData.append('file', file)
	//文件类型 0 表示图片
	formData.append('fileType', '0')

	fileUpload(formData).then((res) => {
		// location.reload();
		toast.success('头像更新成功')
		getPlaygoer(playgoerId).then((res) => {
			playgoer.value = res.data
		})
		dialogForAvatar.value = false
	})
	return Promise.resolve()
}

const open = () => {
	updateForm.playgoerName = playgoer.value.playgoerName as string
	updateForm.playgoerInfo = playgoer.value.playgoerInfo as string
	updateForm.playgoerSex = playgoer.value.playgoerSex as string
	updateForm.playgoerBirthday = playgoer.value.playgoerBirthday as any
	dialogVisible.value = true
}
const handleClose = (done: () => void) => {
	ElMessageBox.confirm('未提交的修改会丢失，确认要关闭吗?')
		.then(() => {
			done()
		})
		.catch(() => {
			// catch error
		})
}

getPlaygoerById()
</script>
