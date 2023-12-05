<template>
	<div>
		<el-card class="box-card">
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
						<el-radio :label="3">女</el-radio>
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
	</div>
</template>
<script setup lang="ts">
import { logout } from '~/api/user'
import router from '~/plugins/router'
import { Playgoer } from '~/api/types/user'
import { UploadRequestOptions } from 'element-plus'

const size = 'large'

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

const Route = useRoute() //获取到值

const dialogVisible = ref(false)
const dialogForAvatar = ref(false)

function getPlaygoerById() {
	if (
		Route.query.id === null ||
		Route.query.id === undefined ||
		Route.query.id === ''
	) {
		flag.value = true
	} else {
		playgoerId = Route.query.id as string
		flag.value = false
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

const toUpload = (param: UploadRequestOptions) => {
	// debugger
	const file = param.file
	const formData = new FormData()
	formData.append('file', file)
	//文件类型 0 表示图片
	formData.append('fileType', '0')

	fileUpload(formData).then((res) => {
		console.log(res);

		// location.reload();
		toast.success('头像更新成功')
		getPlaygoer(playgoerId).then((res) => {
			playgoer.value = res.data
		})
		dialogForAvatar.value=false

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
