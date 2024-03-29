<template>
	<div class="upload-container">
		<div class="select-container" style="width: 25%">
			<el-select
				v-model="value"
				class="m-2"
				placeholder="请选择文件类型"
				size="large"
			>
				<el-option
					v-for="item in options"
					:key="item.value"
					:label="item.label"
					:value="item.value"
				/>
			</el-select>
			<el-select
				v-model="type"
				class="m-2"
				placeholder="请选择戏曲类目"
				size="large"
			>
				<el-option
					v-for="item in typeList"
					:key="item.typeId"
					:label="item.typeName"
					:value="item.typeId as string"
				/>
			</el-select>
		</div>
		<el-input
			v-model="textarea"
			:rows="2"
			autosize
			maxlength="200"
			show-word-limit
			type="textarea"
			placeholder="请输入戏曲简介"
			style="width: 25%"
		/>
		<el-upload
			class="upload-demo"
			drag
			ref="upload"
			:http-request="toUpload"
			:limit="1"
			element-loading-text="正在上传"
			style="width: 25%"
		>
			<!-- <el-icon class="el-icon--upload"><upload-filled /></el-icon> -->
			<div class="i-mdi:cloud-upload-outline el-icon--upload"></div>
			<div class="el-upload__text">拖动文件到此或 <em>点击上传</em></div>
			<!-- <template #trigger>
				<el-button type="primary">select file</el-button>
			</template>
			<el-button class="ml-3" type="success" @click="submitUpload">
				upload to server
			</el-button> -->

			<template #tip>
				<div class="el-upload__tip">
					<span style="color: white; text-shadow: 2px 2px 2px black;">
						只能上传音频或视频文件，且不超过 500MB
					</span>
				</div>
			</template>
			<!-- <el-button class="ml-3" type="success" @click="submitUpload">
				upload to server
			</el-button> -->
		</el-upload>
	</div>
</template>
<script setup lang="ts">
import { OperaType } from '~/api/types/type'
import { ref } from 'vue'
import type { UploadInstance, UploadRequestOptions } from 'element-plus'
import router from '~/plugins/router'

const value = ref('')
const type = ref('')
const upload = ref<UploadInstance>()
const options = [
	{
		value: '1',
		label: '音频',
	},
	{
		value: '2',
		label: '视频',
	},
]
const typeList = ref<OperaType[]>()
const textarea = ref('')
// const uploadForm = reactive({
// 	file: null,
// 	fileType: value.value,
// 	operaType: type.value,
// 	textarea: textarea.value,
// })

function isLogin() {
	//判断用户是否登录
	const token = localStorage.getItem('token')
	if (token === null || token === '' || token === undefined) {
		//未登录
		toast.warning('请先登录')
		return
	} else {
		const playgoerId = localStorage.getItem('playgoerId')
	}
}
function getAllOperaType() {
	getAllType().then((res) => {
		typeList.value = res.data
	})
}

const toUpload = (param: UploadRequestOptions) => {
	isLogin()
	// debugger
	const file = param.file
	const formData = new FormData()
	formData.append('file', file)
	formData.append('fileType', value.value)
	formData.append('operaType', type.value)
	formData.append('textarea', textarea.value)

	const loading = ElLoading.service({
		lock: true,
		text: 'Loading',
		background: 'rgba(0, 0, 0, 0.7)',
	})
	loading.lock.value = true
	fileUpload(formData).then((res) => {
		console.log(res)
		toast.success('上传成功')
		loading.close()
		router.replace({ path: '/empty', query: { path: 'upload' } })
	})
	return Promise.resolve()
}
// const submitUpload = () => {
// 	const jsonStr = JSON.stringify(uploadForm.data);
//     const blob = new Blob([jsonStr], {
//         type: 'application/json'
//     });
//     let formData = new FormData();
//     formData.append("obj", blob);
//     // 这里很重要 file.value.raw才是真实的file文件，file.value只是一个Proxy代理对象
//     formData.append("file", file.value.raw);

// 	fileUpload(formData).then((res) => {
// 		console.log(res)
// 	})
// }

isLogin()
getAllOperaType()
</script>
<style scoped>
.upload-container::after {
	content: '';
	background: url('~/assets/images/upload-bg.jpg') no-repeat center center;
	background-size: cover;
	opacity: 0.5;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	position: absolute;
	z-index: -1;
}
.upload-container {
	width: 100vw;
	height: calc(100vh - 80px);
	position: relative;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	padding: 20px;
	box-sizing: border-box;
	gap: 20px;
}
.select-container {
	display: flex;
	gap: 20px;
}
</style>
