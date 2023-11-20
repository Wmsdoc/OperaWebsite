<template>
	<div>
		<div>
			<el-button
				type="primary"
				style="margin-left: 16px"
				@click="drawer = true"
			>
				with footer
			</el-button>
			<el-drawer v-model="drawer" :direction="direction">
				<template #header>
					<h4>set title by slot</h4>
				</template>
				<template #default>
					<div>
						<el-radio v-model="radio1" label="Option 1" size="large"
							>Option 1</el-radio
						>
						<el-radio v-model="radio1" label="Option 2" size="large"
							>Option 2</el-radio
						>
					</div>
				</template>
				<template #footer>
					<div style="flex: auto">
						<el-button @click="cancelClick">cancel</el-button>
						<el-button type="primary" @click="confirmClick">confirm</el-button>
					</div>
				</template>
			</el-drawer>
		</div>
		<div>这是分类</div>
		<div>
			<ul>
				<li v-for="item in typeList" :key="item.typeId">
					{{ item.typeName }}
				</li>
			</ul>
		</div>
	</div>
</template>
<script setup lang="ts">
import { OperaType } from '~/api/classify'
let typeList = ref<OperaType[]>()
const loading = ref(false)
const drawer = ref(true)
const direction = 'rtl'
const radio1 = ref('Option 1')

function getAllOperaType() {
	loading.value = true
	http
		.get('/opera/type/getAllOperaType')
		.then((res) => {
			typeList.value = res.data
			console.log(typeList.value)
		})
		.finally(() => {
			loading.value = false
		})
}

function cancelClick() {
	drawer.value = false
}
function confirmClick() {
	ElMessageBox.confirm(`Are you confirm to chose ${radio1.value} ?`)
		.then(() => {
			drawer.value = false
		})
		.catch(() => {
			// catch error
		})
}

getAllOperaType()
</script>
