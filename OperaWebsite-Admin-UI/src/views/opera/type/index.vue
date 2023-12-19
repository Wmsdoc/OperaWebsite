<template>
  <div>
    <CommonPage title="戏曲类型管理">
      <!-- 查询 -->
      <n-space>
        <n-input
          v-model:value="queryParams.typeName"
          placeholder="根据类型名搜索"
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
        ref="TypeTable"
        remote
        :columns="columns"
        :data="data"
        :loading="loading"
        :pagination="pagination"
        :max-height="500"
      />
      <!-- 编辑 -->
      <n-modal v-model:show="dialogVisible" preset="dialog" title="编辑">
        <n-form
          ref="editFormRef"
          label-placement="left"
          label-align="left"
          :label-width="80"
          :model="editForm"
        >
          <n-form-item label="类型名" path="filename">
            <n-input v-model:value="editForm.typeName" />
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
import { NButton, NTag } from 'naive-ui'
import api from './api'
import { h, reactive, ref } from 'vue'
import { renderIcon } from '@/utils'

//表格字段
const columns = [
  {
    title: '类型名',
    key: 'typeName',
    width: 100,
    ellipsis: { tooltip: true },
    render(row) {
      return h(
        NTag,
        {
          bordered: false,
          type: 'info',
          onClick: () => handleDetail(row),
        },
        { default: () => row.typeName }
      )
    },
  },
  {
    title: '音频数量',
    key: 'audioCount',
    width: 80,
    ellipsis: { tooltip: true },
  },
  {
    title: '视频数量',
    key: 'videoCount',
    width: 80,
    ellipsis: { tooltip: true },
  },
  {
    title: '创建时间',
    key: 'createdAt',
    width: 150,
    ellipsis: { tooltip: true },
  },
  {
    title: '最新修改时间',
    key: 'updatedAt',
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
            type: 'primary',
            style: 'margin-left: 15px;',
            onClick: () => handleEdit(row),
          },
          { default: () => '编辑', icon: renderIcon('material-symbols:edit-outline', { size: 14 }) }
        ),

        h(
          NButton,
          {
            size: 'small',
            type: 'error',
            style: 'margin-left: 15px;',
            onClick: () => handleDelete(row.typeId),
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
  typeId: null,
  typeName: '',
})
//查询参数

const queryParams = ref({
  typeName: '',
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
    getOperaTypeList()
  },
  onUpdatePageSize: (pageSize) => {
    pagination.pageSize = pageSize
    pagination.page = 1
    getOperaTypeList()
  },
})

//获取戏曲视频评论列表
function getOperaTypeList() {
  if (!loading.value) loading.value = true
  api
    .getPosts({
      pageNum: pagination.page,
      pageSize: pagination.pageSize,
      typeName: queryParams.value.typeName,
    })
    .then((res) => {
      data.value = res.data.records
      pagination.itemCount = res.data.total
      loading.value = false
    })
}

//查询
const handleSearch = () => {
  getOperaTypeList()
}

//修改戏曲类型信息
const handleEdit = (row) => {
  editForm.typeId = row.typeId
  editForm.typeName = row.typeName
  dialogVisible.value = true
}
const handleDelete = (typeId) => {
  $dialog.confirm({
    content: '确定删除？删除后，该类型下的音频和视频不会受到影响',
    confirm() {
      api.deletePost(typeId).then((res) => {
        if (res.code === 200 && res.data === true) {
          console.log(res)
          $message.success('删除成功')
          getOperaTypeList()
        } else {
          $message.error('删除失败')
        }
      })
    },
  })
}
const handleUpdate = () => {
  let formData = new FormData()
  formData.append('typeId', editForm.typeId)
  formData.append('typeName', editForm.typeName)
  api.updatePost(formData).then((res) => {
    if (res.code === 200 && res.data === true) {
      $message.success('修改成功')
      dialogVisible.value = false
      getOperaTypeList()
    } else {
      $message.error('修改失败')
    }
  })
}
const handleDetail = () => {
  console.log('详情')
}

getOperaTypeList()
</script>
