<template>
  <div>
    <CommonPage title="戏曲视频列表">
      <!-- 查询 -->
      <n-space>
        <n-input
          v-model:value="queryParams.filename"
          placeholder="搜索"
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
        <n-select
          v-model:value="queryParams.typeId"
          placeholder="选择戏曲类型"
          :options="options"
          label-field="typeName"
          value-field="typeId"
          clearable
          remote
          filterable
          :loading="loadingSelect"
          @update:value="handleSearch"
          @focus="getAllOperaType"
        />
      </n-space>
      <n-divider />
      <n-data-table
        ref="VideoTable"
        remote
        :columns="columns"
        :data="data"
        :loading="loading"
        :pagination="pagination"
        :max-height="500"
      />
      <!-- 详情 -->
      <n-modal v-model:show="showModal" preset="dialog" title="详情">
        <n-avatar round size="large" :src="details.playgoerAvatar" />
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
            音频：{{ details.typeName }}--{{ details.filename }}
          </p>
          <p class="demo-rich-content__name" style="margin: 0; font-weight: 500">
            简介：{{ details.videoInfo }}
          </p>
          <p class="demo-rich-content__name" style="margin: 0; font-weight: 500">
            下载量：{{ details.downloadNum }}
          </p>
        </div>

        <template #action>
          <p class="demo-rich-content__desc">上传时间：{{ details.createdAt }}</p>
          <p class="demo-rich-content__desc">最新修改时间：{{ details.updatedAt }}</p>
        </template>
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
          <n-form-item label="视频名" path="filename">
            <n-input v-model:value="editForm.filename" />
          </n-form-item>
          <n-form-item label="视频简介" path="videoInfo">
            <n-input
              v-model:value="editForm.videoInfo"
              type="textarea"
              maxlength="200"
              show-count
            />
          </n-form-item>
          <n-form-item label="视频类型" path="typeName">
            <n-select
              v-model:value="editForm.typeName"
              placeholder="选择戏曲类型"
              :options="options"
              label-field="typeName"
              value-field="typeId"
              remote
              :loading="loadingSelect"
              @focus="getAllOperaType"
              @update:value="editForm.typeId = $event"
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
import { NButton, NSwitch, NText } from 'naive-ui'
import api from './api'
import { h, reactive, ref } from 'vue'
import { isNullOrUndef, renderIcon } from '@/utils'

//表格字段
const columns = [
  {
    title: '发布',
    key: 'isExamine',
    width: 60,
    align: 'center',
    fixed: 'left',
    render(row) {
      return h(NSwitch, {
        size: 'small',
        rubberBand: false,
        checkedValue: 1,
        uncheckedValue: 0,
        value: row['isExamine'],
        loading: !!row.examine,
        onUpdateValue: () => handleExamine(row),
      })
    },
  },
  {
    title: '文件名',
    key: 'filename',
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
        { default: () => row.filename }
      )
    },
  },
  {
    title: '下载路径',
    key: 'downloadUrl',
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
            onClick: () => handleView(row.videoId),
          },
          { default: () => '查看', icon: renderIcon('majesticons:eye-line', { size: 14 }) }
        ),
        h(
          NButton,
          {
            size: 'small',
            type: 'primary',
            style: 'margin-left: 15px;',
            onClick: () => handleEdit(row.videoId),
          },
          { default: () => '编辑', icon: renderIcon('material-symbols:edit-outline', { size: 14 }) }
        ),

        h(
          NButton,
          {
            size: 'small',
            type: 'error',
            style: 'margin-left: 15px;',
            onClick: () => handleDelete(row.videoId),
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
//戏曲类型
const options = ref([])
//表格数据
const data = ref([])
//详情数据
const details = ref({})
const loading = ref(true)
const loadingSelect = ref(false)
//弹窗 详情
const showModal = ref(false)
const dialogVisible = ref(false)

//编辑form
const editForm = reactive({
  typeId: '',
  typeName: '',
  videoId: '',
  filename: '',
  videoInfo: '',
})
//查询参数

const queryParams = ref({
  typeId: null,
  timeFlag: null,
  filename: '',
})

//分页
const pagination = reactive({
  page: 1,
  pageSize: 5,
  pageSizes: [3, 5, 10],
  showSizePicker: true,
  prefix({ itemCount }) {
    return `总条数： ${itemCount}`
  },
  onChange: (page) => {
    pagination.page = page
    getOperaVideoList()
  },
  onUpdatePageSize: (pageSize) => {
    pagination.pageSize = pageSize
    pagination.page = 1
    getOperaVideoList()
  },
})

//获取戏曲音频列表
function getOperaVideoList() {
  if (!loading.value) loading.value = true
  api
    .getPosts({
      pageNum: pagination.page,
      pageSize: pagination.pageSize,
      typeId: queryParams.value.typeId,
      timeFlag: queryParams.value.timeFlag,
      filename: queryParams.value.filename,
    })
    .then((res) => {
      console.log(res)
      data.value = res.data.records
      pagination.itemCount = res.data.total
      loading.value = false
    })
}
function getAllOperaType() {
  if (options.value.length != 0) return
  loadingSelect.value = true
  api.getOperaType().then((res) => {
    options.value = res.data
    // options.value.unshift({ typeName: '全部', typeId: 0 })
    loadingSelect.value = false
  })
}
//查询
const handleSearch = () => {
  getOperaVideoList()
}
//审核
const handleExamine = (row) => {
  if (isNullOrUndef(row.videoId)) return
  row.examine = true
  let formData = new FormData()
  formData.append('videoId', row.videoId)
  formData.append('isExamine', !row.isExamine ? 1 : 0)
  api.updatePost(formData).then((res) => {
    if (res.code === 200 && res.data === true) {
      row.isExamine = !row.isExamine ? 1 : 0
      row.examine = false
      $message?.success(row.isExamine ? '已发布' : '已取消发布')
    } else {
      $message.error('失败')
    }
  })
  setTimeout(() => {}, 1000)
}
const handleView = (videoId) => {
  api.getPostById(videoId).then((res) => {
    showModal.value = true
    details.value = res.data
    console.log(details.value)
  })
}

const handleEdit = (videoId) => {
  //重置数据
  editForm.filename = ''
  editForm.videoInfo = ''
  api.getVideoById(videoId).then((res) => {
    editForm.videoId = res.data.videoId
    editForm.filename = res.data.filename
    editForm.videoInfo = res.data.videoInfo
    editForm.isExamine = res.data.isExamine
    editForm.typeName = res.data.typeName
    console.log(editForm)
    dialogVisible.value = true
  })
}
const handleDelete = (videoId) => {
  $dialog.confirm({
    content: '确认删除？',
    confirm() {
      api.deletePost(videoId).then((res) => {
        if (res.code === 200 && res.data === true) {
          console.log(res)
          $message.success('删除成功')
          getOperaVideoList()
        } else {
          $message.error('删除失败')
        }
      })
    },
  })
}
const handleUpdate = () => {
  let formData = new FormData()
  formData.append('videoId', editForm.videoId)
  formData.append('filename', editForm.filename)
  formData.append('videoInfo', editForm.videoInfo)
  formData.append('typeId', editForm.typeId)
  api.updatePost(formData).then((res) => {
    if (res.code === 200 && res.data === true) {
      $message.success('修改成功')
      dialogVisible.value = false
      getOperaVideoList()
    } else {
      $message.error('修改失败')
    }
  })
}
const handleDetail = () => {
  console.log('详情')
}

getOperaVideoList()
</script>
