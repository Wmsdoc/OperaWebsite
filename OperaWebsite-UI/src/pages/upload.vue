<template>
	<div>
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
				:value="(item.typeId as string)"
			/>
		</el-select>
		<el-input
			v-model="textarea"
			:rows="2"
			autosize
			maxlength="200"
			show-word-limit
			type="textarea"
			placeholder="请输入戏曲简介"
		/>
		<el-upload
			class="upload-demo"
			drag
			ref="upload"
			:http-request="toUpload"
			:limit="1"
			element-loading-text="正在上传"
		>
			<!-- <el-icon class="el-icon--upload"><upload-filled /></el-icon> -->
			<div class="i-mdi:cloud-upload-outline el-icon--upload"></div>
			<div class="el-upload__text">
				Drop file here or <em>click to upload</em>
			</div>
			 <!-- <template #trigger>
				<el-button type="primary">select file</el-button>
			</template>
			<el-button class="ml-3" type="success" @click="submitUpload">
				upload to server
			</el-button> -->

			<template #tip>
				<div class="el-upload__tip">
					jpg/png files with a size less than 500kb
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
import type {
	UploadInstance,
	UploadRequestOptions,
} from 'element-plus'
import router from '~/plugins/router';

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
const uploadForm = reactive({
	file: null,
	fileType: value.value,
	operaType: type.value,
	textarea: textarea.value,
})

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
	const formData = new FormData();
	formData.append('file', file)
	formData.append('fileType', value.value)
	formData.append('operaType', type.value)
	formData.append('textarea', textarea.value)

	fileUpload(formData).then((res) => {
		console.log(res)
		router.replace('/upload')
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
