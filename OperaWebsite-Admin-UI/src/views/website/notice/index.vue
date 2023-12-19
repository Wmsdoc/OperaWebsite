<template>
  <div>
    <CommonPage title="戏曲网站公告">
      <!-- 新增 -->
      <n-space>
        <div>
          <n-button type="primary" class="ml-16" @click="handleAdd">
            <TheIcon icon="material-symbols:add" :size="18" class="mr-5" />
            发布公告
          </n-button>
        </div>
      </n-space>
      <n-divider />
      <n-data-table
        ref="TypeTable"
        remote
        :columns="columns"
        :data="data"
        :loading="loading"
        :pagination="pagination"
        :max-height="500"
      />
      <!-- 新增 -->
      <n-modal v-model:show="dialogVisible" preset="dialog" title="新增">
        <n-form
          ref="editFormRef"
          label-placement="left"
          label-align="left"
          :label-width="80"
          :model="editForm"
        >
          <n-form-item label="网站公告" path="noticeContent">
            <n-input
              v-model:value="editForm.noticeContent"
              placeholder="请输入网站公告"
              type="textarea"
              maxlength="200"
              show-count
            />
          </n-form-item>
        </n-form>
        <template #action>
          <n-button type="primary" @click="dialogVisible = false">取消</n-button>
          <n-button type="primary" @click="doCreate">确定</n-button>
        </template>
      </n-modal>
    </CommonPage>
  </div>
</template>

<script setup>
import { NButton, NTag } from 'naive-ui'
import api from './api'
import { h, reactive, ref } from 'vue'
import { renderIcon } from '@/utils'

//表格字段
const columns = [
  {
    title: '网站公告',
    key: 'noticeContent',
    width: 300,
    ellipsis: { tooltip: true },
    render(row) {
      return h(
        NTag,
        {
          bordered: false,
          type: 'info',
          onClick: () => handleDetail(row),
        },
        { default: () => row.noticeContent }
      )
    },
  },
  {
    title: '公告发布时间',
    key: 'noticeCreatedAt',
    width: 150,
    ellipsis: { tooltip: true },
  },
  {
    title: '操作',
    key: 'actions',
    width: 200,
    align: 'center',
    fixed: 'right',
    hideInExcel: true,
    render(row) {
      return [
        h(
          NButton,
          {
            size: 'small',
            type: 'error',
            style: 'margin-left: 15px;',
            onClick: () => handleDelete(row.noticeId),
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
const loading = ref(true)
const dialogVisible = ref(false)

//编辑form
const editForm = reactive({
  noticeContent: null,
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
    getNoticeList()
  },
  onUpdatePageSize: (pageSize) => {
    pagination.pageSize = pageSize
    pagination.page = 1
    getNoticeList()
  },
})

//获取戏曲视频评论列表
function getNoticeList() {
  if (!loading.value) loading.value = true
  api
    .getPosts({
      pageNum: pagination.page,
      pageSize: pagination.pageSize,
    })
    .then((res) => {
      console.log(res)
      data.value = res.data.records
      pagination.itemCount = res.data.total
      loading.value = false
    })
}

const handleDelete = (noticeId) => {
  $dialog.confirm({
    content: '确定删除？',
    confirm() {
      api.deletePost(noticeId).then((res) => {
        if (res.code === 200 && res.data === true) {
          console.log(res)
          $message.success('删除成功')
          getNoticeList()
        } else {
          $message.error('删除失败')
        }
      })
    },
  })
}
const doCreate = () => {
  let formData = new FormData()
  formData.append('noticeContent', editForm.noticeContent)
  //新增
  api.addPost(formData).then((res) => {
    if (res.code === 200 && res.data === true) {
      $message.success('新增成功')
      dialogVisible.value = false
      getNoticeList()
    } else {
      $message.error('新增失败')
    }
  })
}

const handleAdd = () => {
  editForm.noticeContent = null
  dialogVisible.value = true
}
const handleDetail = () => {
  console.log('详情')
}

getNoticeList()
</script>
