<template>
	<div class="activity-container">
		<div
			style="
				display: flex;
				justify-content: center;
				align-items: center;
				width: 70%;
			"
		>
			<el-input
				v-model="queryParams.activityName"
				style="flex: 1; flex-basis: 70%; height: 40px"
				placeholder="请输入活动名称"
				clearable
				@keyup.enter="Search"
			>
				<template #append>
					<button class="i-material-symbols:search" @click="Search"></button>
				</template>
			</el-input>
			<el-divider direction="vertical" />
			<el-input
				v-model="queryParams.activityAddress"
				style="flex: 1; flex-basis: 70%; height: 40px"
				placeholder="请输入活动地址"
				clearable
				@keyup.enter="Search"
			>
				<template #append>
					<button class="i-material-symbols:search" @click="Search"></button>
				</template>
			</el-input>
		</div>
		<el-divider />
		<el-card style="width: 70%">
			<template #header>
				<div class="card-header">
					<span>戏曲活动</span>
				</div>
			</template>
			<!-- <el-empty description="暂无数据" /> -->
			<el-table ref="multipleTableRef" :data="activityList" style="width: 100%">
				<!-- <el-table-column type="selection" width="55" /> -->
				<el-table-column
					property="activityName"
					label="活动名称"
					style="width: 20%"
				/>
				<el-table-column
					property="activityAddress"
					label="活动地址"
					style="width: 30%"
				/>
				<el-table-column
					property="startTime"
					label="活动开始时间"
					style="width: 20%"
				/>
				<el-table-column
					property="endTime"
					label="活动结束时间"
					style="width: 20%"
				/>
				<el-table-column fixed="right" label="操作" style="width: 10%">
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
									@mouseenter="enter(scope.row.activityId)"
									>详情</el-button
								>
							</template>
							<template #default>
								<div
									class="demo-rich-conent"
									style="display: flex; gap: 16px; flex-direction: column"
								>
									<p class="demo-rich-content__desc" style="margin: 0">
										最新更新：{{ activityDetails.updatedAt }}
									</p>
									<el-avatar
										:size="60"
										:src="activityDetails.playgoerAvatar"
										style="margin-bottom: 8px"
									/>
									<div>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											发布者：{{ activityDetails.playgoerName }}
										</p>
										<p
											class="demo-rich-content__mention"
											style="
												margin: 0;
												font-size: 14px;
												color: var(--el-color-info);
											"
										>
											签名：{{ activityDetails.playgoerInfo }}
										</p>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											活动：{{ activityDetails.activityName }}--{{
												activityDetails.activityAddress
											}}
										</p>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											简介：{{ activityDetails.activityInfo }}
										</p>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											活动剩余名额：{{ activityDetails.activityQuota }}
										</p>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											活动开始时间：{{ activityDetails.startTime }}
										</p>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											活动结束时间：{{ activityDetails.endTime }}
										</p>
										<p
											class="demo-rich-content__name"
											style="margin: 0; font-weight: 500"
										>
											活动总人数：{{ activityDetails.activityNum }}
										</p>
									</div>

									<p class="demo-rich-content__desc" style="margin: 0">
										发布时间：{{ activityDetails.createdAt }}
									</p>
								</div>
							</template>
						</el-popover>

						<el-button
							link
							type="primary"
							size="small"
							@click="insert(scope.row.activityId, scope.row.activityQuota)"
							>报名</el-button
						>
					</template>
				</el-table-column>
			</el-table>
			<el-divider />
			<div class="demo-pagination-block">
				<el-pagination
					v-model:current-page="currentPage"
					v-model:page-size="pageSize"
					:page-sizes="[5, 10, 15, 20]"
					:small="small"
					:background="background"
					layout="total, sizes, prev, pager, next, jumper"
					:total="total"
					@size-change="handleSizeChange"
					@current-change="handleCurrentChange"
				/>
			</div>
		</el-card>
	</div>
</template>
<script setup lang="ts">
import { Activity, ActivityVO } from '~/api/types/activity'

//分页
const currentPage = ref(0)
const pageSize = ref(5)
const total = ref(0)
const small = ref(true)
const background = ref(false)

//活动列表
const activityList = ref<Activity[]>()
//活动详情
const activityDetails = ref<ActivityVO>({})
//条件查询
const queryParams = reactive({
	activityName: '',
	activityAddress: '',
	pageSize: pageSize.value,
	pageNum: currentPage.value,
})

const insert = (activityId: any, activityQuota: number) => {
	if (!localStorage.getItem('playgoerId')) {
		toast.warning('请先登录')
		return
	}
	if (activityQuota <= 0) {
		toast.warning('人数已满')
		return
	}
	let playgoerId = localStorage.getItem('playgoerId')

	let formData = new FormData()
	formData.append('activityId', activityId)
	formData.append('playgoerId', playgoerId as string)
	insertPlaygoerActivity(formData).then((res) => {
		toast.success(res.data)
	})
}

const Search = () => {
	activityList.value = []
	currentPage.value = 1
	pageSize.value = 5
	console.log(queryParams)

	getActivity()
}

const handleSizeChange = (val: number) => {
	queryParams.pageSize = val
	getActivities(queryParams).then((res) => {
		activityList.value = res.data.records
		console.log(activityList.value)
		total.value = res.data.total
	})
}
const handleCurrentChange = (val: number) => {
	queryParams.pageNum = val
	getActivities(queryParams).then((res) => {
		activityList.value = res.data.records
		console.log(activityList.value)
		total.value = res.data.total
	})
}
//鼠标悬浮事件
const enter = (id: number) => {
	getActivityDetails(id).then((res) => {
		activityDetails.value = res.data
	})
}
function getActivity() {
	getActivities(queryParams).then((res) => {
		console.log(res)
		activityList.value = res.data.records
		total.value = res.data.total
	})
}
getActivity()
</script>

<style scoped>
.activity-container::after {
	content: '';
	background: url('~/assets/images/activity-bg.jpg') no-repeat center center;
	background-size: cover;
	opacity: 0.5;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	position: absolute;
	z-index: -1;
}
.activity-container {
	width: 100vw;
	height: calc(100vh - 80px);
	position: relative;
	display: flex;
	flex-direction: column; /* 添加这一行 */
	justify-content: center;
	align-items: center;
}
</style>
