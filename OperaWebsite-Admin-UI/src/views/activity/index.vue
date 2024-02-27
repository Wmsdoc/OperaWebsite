<template>
  <div>
    <CommonPage>
      <!-- 查询 -->
      <n-space>
        <div>
          <n-button type="primary" class="ml-16" @click="handleAdd">
            <TheIcon icon="material-symbols:add" :size="18" class="mr-5" />
            新建活动
          </n-button>
        </div>
        <n-input
          v-model:value="queryParams.activityName"
          placeholder="根据活动名称搜索"
          clearable
          :on-clear="handleSearch"
          @keyup.enter="handleSearch"
        >
          <template #prefix>
            <n-icon size="20">
              <svg
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                x="0px"
                y="0px"
                viewBox="0 0 512 512"
                enable-background="new 0 0 512 512"
                xml:space="preserve"
              >
                <path
                  d="M443.5,420.2L336.7,312.4c20.9-26.2,33.5-59.4,33.5-95.5c0-84.5-68.5-153-153.1-153S64,132.5,64,217s68.5,153,153.1,153
                  c36.6,0,70.1-12.8,96.5-34.2l106.1,107.1c3.2,3.4,7.6,5.1,11.9,5.1c4.1,0,8.2-1.5,11.3-4.5C449.5,437.2,449.7,426.8,443.5,420.2z
                  M217.1,337.1c-32.1,0-62.3-12.5-85-35.2c-22.7-22.7-35.2-52.9-35.2-84.9c0-32.1,12.5-62.3,35.2-84.9c22.7-22.7,52.9-35.2,85-35.2
                  c32.1,0,62.3,12.5,85,35.2c22.7,22.7,35.2,52.9,35.2,84.9c0,32.1-12.5,62.3-35.2,84.9C279.4,324.6,249.2,337.1,217.1,337.1z"
                ></path>
              </svg>
            </n-icon>
          </template>
        </n-input>
        <n-input
          v-model:value="queryParams.activityAddress"
          placeholder="根据活动地点搜索"
          clearable
          :on-clear="handleSearch"
          @keyup.enter="handleSearch"
        >
          <template #prefix>
            <n-icon size="20">
              <svg
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                x="0px"
                y="0px"
                viewBox="0 0 512 512"
                enable-background="new 0 0 512 512"
                xml:space="preserve"
              >
                <path
                  d="M443.5,420.2L336.7,312.4c20.9-26.2,33.5-59.4,33.5-95.5c0-84.5-68.5-153-153.1-153S64,132.5,64,217s68.5,153,153.1,153
                  c36.6,0,70.1-12.8,96.5-34.2l106.1,107.1c3.2,3.4,7.6,5.1,11.9,5.1c4.1,0,8.2-1.5,11.3-4.5C449.5,437.2,449.7,426.8,443.5,420.2z
                  M217.1,337.1c-32.1,0-62.3-12.5-85-35.2c-22.7-22.7-35.2-52.9-35.2-84.9c0-32.1,12.5-62.3,35.2-84.9c22.7-22.7,52.9-35.2,85-35.2
                  c32.1,0,62.3,12.5,85,35.2c22.7,22.7,35.2,52.9,35.2,84.9c0,32.1-12.5,62.3-35.2,84.9C279.4,324.6,249.2,337.1,217.1,337.1z"
                ></path>
              </svg>
            </n-icon>
          </template>
        </n-input>
      </n-space>
      <n-divider />
      <n-data-table
        ref="AudioTable"
        remote
        :columns="columns"
        :data="data"
        :loading="loading"
        :pagination="pagination"
        :max-height="500"
      />
      <!-- 详情 -->
      <n-modal v-model:show="showModal" preset="dialog" title="详情">
        <div class="demo-rich-conent" style="display: flex; gap: 16px; flex-direction: column">
          <p class="demo-rich-content__desc" style="margin: 0">最新更新：{{ details.updatedAt }}</p>
          <n-avatar :size="60" :src="details.playgoerAvatar" style="margin-bottom: 8px" />
          <div>
            <p class="demo-rich-content__name" style="margin: 0; font-weight: 500">
              发布者：{{ details.playgoerName }}
            </p>
            <p
              class="demo-rich-content__mention"
              style="margin: 0; font-size: 14px; color: var(--el-color-info)"
            >
              签名：{{ details.playgoerInfo }}
            </p>
            <p class="demo-rich-content__name" style="margin: 0; font-weight: 500">
              活动：{{ details.activityName }}--{{ details.activityAddress }}
            </p>
            <p class="demo-rich-content__name" style="margin: 0; font-weight: 500">
              简介：{{ details.activityInfo }}
            </p>
            <p class="demo-rich-content__name" style="margin: 0; font-weight: 500">
              活动剩余名额：{{ details.activityQuota }}
            </p>
            <p class="demo-rich-content__name" style="margin: 0; font-weight: 500">
              活动开始时间：{{ details.startTime }}
            </p>
            <p class="demo-rich-content__name" style="margin: 0; font-weight: 500">
              活动结束时间：{{ details.endTime }}
            </p>
            <p class="demo-rich-content__name" style="margin: 0; font-weight: 500">
              活动总人数：{{ details.activityNum }}
            </p>
          </div>

          <p class="demo-rich-content__desc" style="margin: 0">发布时间：{{ details.createdAt }}</p>
        </div>
      </n-modal>
      <!-- 编辑 -->
      <n-modal v-model:show="dialogVisible" preset="dialog" title="编辑">
        <n-form
          ref="editFormRef"
          label-placement="left"
          label-align="left"
          :label-width="80"
          :model="editForm"
        >
          <n-form-item label="活动名称" path="activityName">
            <n-input v-model:value="editForm.activityName" placeholder="请输入活动名称" />
          </n-form-item>
          <n-form-item label="活动地点" path="activityAddress">
            <n-input v-model:value="editForm.activityAddress" placeholder="请输入活动地点" />
          </n-form-item>
          <n-form-item label="活动简介" path="activityInfo">
            <n-input
              v-model:value="editForm.activityInfo"
              placeholder="请输入活动简介"
              type="textarea"
              maxlength="200"
              show-count
            />
          </n-form-item>
          <n-form-item label="活动人数" path="activityNum">
            <n-input-number v-model:value="editForm.activityNum" placeholder="请输入活动人数">
              <template #suffix>人</template>
            </n-input-number>
          </n-form-item>
          <n-form-item label="开始时间" path="startTime">
            <n-date-picker
              v-model:formatted-value="editForm.startTime"
              placeholder="请选择开始时间"
              value-format="yyyy-MM-dd HH:mm"
              type="datetime"
              clearable
            />
          </n-form-item>
          <n-form-item label="结束时间" path="endTime">
            <n-date-picker
              v-model:formatted-value="editForm.endTime"
              placeholder="请选择结束时间"
              value-format="yyyy-MM-dd HH:mm"
              type="datetime"
              clearable
            />
          </n-form-item>
        </n-form>
        <template #action>
          <n-button type="primary" @click="dialogVisible = false">取消</n-button>
          <n-button type="primary" @click="handleUpdate">确定</n-button>
        </template>
      </n-modal>
    </CommonPage>
  </div>
</template>

<script setup>
import { NButton, NText } from 'naive-ui'
import api from './api'
import { h, reactive, ref } from 'vue'
import { renderIcon } from '@/utils'

//表格字段
const columns = [
  {
    title: '活动名称',
    key: 'activityName',
    width: 150,
    ellipsis: { tooltip: true },
    render(row) {
      return h(
        NText,
        {
          bordered: 'false',
          type: 'info',
          onClick: () => handleDetail(row),
        },
        { default: () => row.activityName }
      )
    },
  },
  {
    title: '活动地点',
    key: 'activityAddress',
    width: 200,
    ellipsis: { tooltip: true },
  },
  {
    title: '开始时间',
    key: 'startTime',
    width: 150,
    ellipsis: { tooltip: true },
  },
  {
    title: '结束时间',
    key: 'endTime',
    width: 150,
    ellipsis: { tooltip: true },
  },
  {
    title: '操作',
    key: 'actions',
    width: 240,
    align: 'center',
    fixed: 'right',
    hideInExcel: true,
    render(row) {
      return [
        h(
          NButton,
          {
            size: 'small',
            type: 'primary',
            secondary: true,
            onClick: () => handleView(row.activityId),
          },
          { default: () => '查看', icon: renderIcon('majesticons:eye-line', { size: 14 }) }
        ),
        h(
          NButton,
          {
            size: 'small',
            type: 'primary',
            style: 'margin-left: 15px;',
            onClick: () => handleEdit(row.activityId),
          },
          { default: () => '编辑', icon: renderIcon('material-symbols:edit-outline', { size: 14 }) }
        ),
        h(
          NButton,
          {
            size: 'small',
            type: 'error',
            style: 'margin-left: 15px;',
            onClick: () => handleDelete(row.activityId),
          },
          {
            default: () => '删除',
            icon: renderIcon('material-symbols:delete-outline', { size: 14 }),
          }
        ),
      ]
    },
  },
]
//表格数据
const data = ref([])
const details = ref({})
const showModal = ref(false)
const loading = ref(true)
const dialogVisible = ref(false)

//编辑form
const editForm = reactive({
  activityId: '',
  activityName: null,
  activityAddress: null,
  activityInfo: null,
  activityNum: null,
  startTime: null,
  endTime: null,
})
//查询参数

const queryParams = ref({
  activityName: '',
  activityAddress: '',
})

//分页
const pagination = reactive({
  page: 1,
  pageSize: 5,
  pageSizes: [5, 10, 15],
  showSizePicker: true,
  prefix({ itemCount }) {
    return `总条数： ${itemCount}`
  },
  onChange: (page) => {
    pagination.page = page
    getActivityList()
  },
  onUpdatePageSize: (pageSize) => {
    pagination.pageSize = pageSize
    pagination.page = 1
    getActivityList()
  },
})

//获取戏曲音频评论列表
function getActivityList() {
  if (!loading.value) loading.value = true
  api
    .getPosts({
      pageNum: pagination.page,
      pageSize: pagination.pageSize,
      activityName: queryParams.value.activityName,
      activityAddress: queryParams.value.activityAddress,
    })
    .then((res) => {
      console.log(res)
      data.value = res.data.records
      pagination.itemCount = res.data.total
      loading.value = false
    })
}

//查询
const handleSearch = () => {
  getActivityList()
}

const handleView = (activityId) => {
  api.getPostById(activityId).then((res) => {
    showModal.value = true
    details.value = res.data
    console.log(details.value)
  })
}

//数据重置
function reset() {
  editForm.activityId = ''
  editForm.activityName = null
  editForm.activityAddress = null
  editForm.activityInfo = null
  editForm.activityNum = null
  editForm.startTime = null
  editForm.endTime = null
}

const handleAdd = () => {
  reset()
  dialogVisible.value = true
}

//修改评论信息
const handleEdit = (activityId) => {
  reset()
  api.getActivityById(activityId).then((res) => {
    editForm.activityId = res.data.activityId
    editForm.activityName = res.data.activityName
    editForm.activityAddress = res.data.activityAddress
    editForm.activityInfo = res.data.activityInfo
    editForm.activityNum = res.data.activityNum
    editForm.startTime = res.data.startTime
    editForm.endTime = res.data.endTime

    dialogVisible.value = true
  })
}
const handleDelete = (activityId) => {
  $dialog.confirm({
    content: '确认删除？',
    confirm() {
      api.deletePost(activityId).then((res) => {
        if (res.code === 200 && res.data === true) {
          $message.success('删除成功')
          getActivityList()
        } else {
          $message.error('删除失败')
        }
      })
    },
  })
}
const handleUpdate = () => {
  let formData = new FormData()
  formData.append('activityName', editForm.activityName)
  formData.append('activityInfo', editForm.activityInfo)
  formData.append('activityAddress', editForm.activityAddress)
  formData.append('activityNum', editForm.activityNum)
  formData.append('startTime', editForm.startTime)
  formData.append('endTime', editForm.endTime)
  if (editForm.activityId === '' || editForm.activityId === undefined) {
    //新增
    api.addPost(formData).then((res) => {
      if (res.code === 200 && res.data === true) {
        $message.success('新增成功')
        dialogVisible.value = false
        getActivityList()
      } else {
        $message.error('新增失败')
      }
    })
  } else {
    formData.append('activityId', editForm.activityId)
    //修改
    api.updatePost(formData).then((res) => {
      if (res.code === 200) {
        if (res.data === 1) {
          $message.success('修改成功')
          dialogVisible.value = false
          getActivityList()
        } else if (res.data === -1) {
          $message.error('人数不能小于已报名人数')
        } else {
          $message.error('修改失败')
        }
      } else {
        $message.error('修改失败')
      }
    })
  }
}
const handleDetail = () => {
  console.log('详情')
}

getActivityList()
</script>
