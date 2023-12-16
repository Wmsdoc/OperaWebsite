<template>
  <div>
    <CommonPage>
      <!-- 查询 -->
      <n-space>
        <n-input
          v-model:value="queryParams.accountUsername"
          placeholder="根据账户名搜索"
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
          v-model:value="queryParams.playgoerName"
          placeholder="根据用户昵称搜索"
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
      <!-- 编辑 -->
      <n-modal v-model:show="dialogVisible" preset="dialog" title="编辑">
        <n-form
          ref="editFormRef"
          label-placement="left"
          label-align="left"
          :label-width="80"
          :model="editForm"
        >
          <n-form-item label="账户名" path="accountUsername">
            <n-input v-model:value="editForm.accountUsername" disabled />
          </n-form-item>
          <n-form-item label="用户昵称" path="playgoerName">
            <n-input v-model:value="editForm.playgoerName" placeholder="请输入用户昵称" />
          </n-form-item>
          <n-form-item label="用户简介" path="playgoerInfo">
            <n-input
              v-model:value="editForm.playgoerInfo"
              placeholder="请输入用户简介"
              type="textarea"
              maxlength="200"
              show-count
            />
          </n-form-item>
          <n-form-item label="性别" path="playgoerSex">
            <n-radio-group v-model:value="editForm.playgoerSex" name="radiogroup">
              <n-space>
                <n-radio :key="editForm.playgoerSex" :value="0">保密</n-radio>
                <n-radio :key="editForm.playgoerSex" :value="1">男</n-radio>
                <n-radio :key="editForm.playgoerSex" :value="2">女</n-radio>
              </n-space>
            </n-radio-group>
          </n-form-item>
          <n-form-item label="出生日期" path="playgoerBirthday">
            <n-date-picker
              v-model:formatted-value="editForm.playgoerBirthday"
              placeholder="请选择开始时间"
              value-format="yyyy-MM-dd"
              type="date"
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
import { NButton, NAvatar, NTag, NText } from 'naive-ui'
import api from './api'
import { h, reactive, ref } from 'vue'
import { renderIcon } from '@/utils'

//表格字段
const columns = [
  {
    title: '账号名',
    key: 'accountUsername',
    width: 120,
    ellipsis: { tooltip: true },
    render(row) {
      return h(
        NText,
        {
          bordered: 'false',
          type: 'info',
          onClick: () => handleDetail(row),
        },
        { default: () => row.accountUsername }
      )
    },
  },
  {
    title: '用户头像',
    key: 'playgoerAvatar',
    width: 80,
    ellipsis: { tooltip: true },
    render(row) {
      return h(
        NAvatar,
        {
          round: true,
          size: 'medium',
          src: row.playgoerAvatar,
          onClick: () => handleDetail(row),
        }
        // { default: () => row.playgoerAvatar }
      )
    },
  },
  {
    title: '用户昵称',
    key: 'playgoerName',
    width: 120,
    ellipsis: { tooltip: true },
    render(row) {
      return h(
        NText,
        {
          bordered: 'false',
          type: 'info',
          onClick: () => handleDetail(row),
        },
        { default: () => row.playgoerName }
      )
    },
  },
  {
    title: '性别',
    key: 'playgoerSex',
    width: 80,
    ellipsis: { tooltip: true },
    render(row) {
      return h(
        NTag,
        {
          bordered: false,
          type: 'info',
          onClick: () => handleDetail(row),
        },
        {
          default: () => {
            if (row.playgoerSex === 0) {
              return '保密'
            } else if (row.playgoerSex === 1) {
              return '男'
            } else if (row.playgoerSex === 2) {
              return '女'
            }
          },
        }
      )
    },
  },
  {
    title: '出生日期',
    key: 'playgoerBirthday',
    width: 120,
    ellipsis: { tooltip: true },
  },
  {
    title: '用户简介',
    key: 'playgoerInfo',
    width: 100,
    ellipsis: { tooltip: true },
  },
  {
    title: '注册时间',
    key: 'createdAt',
    width: 120,
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
      if (row.accountStatue === 0) {
        return [
          h(
            NButton,
            {
              size: 'small',
              type: 'primary',
              style: 'margin-left: 15px;',
              onClick: () => handleEdit(row),
            },
            {
              default: () => '编辑',
              icon: renderIcon('material-symbols:edit-outline', { size: 14 }),
            }
          ),
          h(
            NButton,
            {
              size: 'small',
              type: 'error',
              style: 'margin-left: 15px;',
              onClick: () => handleUpdateStatue(row.playgoerId),
            },
            {
              default: () => '封禁',
              icon: renderIcon('material-symbols:delete-outline', { size: 14 }),
            }
          ),
        ]
      } else {
        return [
          h(
            NButton,
            {
              size: 'small',
              type: 'primary',
              style: 'margin-left: 15px;',
              onClick: () => handleEdit(row),
            },
            {
              default: () => '编辑',
              icon: renderIcon('material-symbols:edit-outline', { size: 14 }),
            }
          ),
          h(
            NButton,
            {
              size: 'small',
              type: 'success',
              style: 'margin-left: 15px;',
              onClick: () => handleUpdateStatue(row.playgoerId),
            },
            {
              default: () => '解封',
              icon: renderIcon('material-symbols:delete-outline', { size: 14 }),
            }
          ),
        ]
      }
    },
  },
]
//表格数据
const data = ref([])
const loading = ref(true)
const dialogVisible = ref(false)

//编辑form
const editForm = reactive({
  accountUsername: '',
  playgoerBirthday: null,
  playgoerId: '',
  playgoerInfo: null,
  playgoerName: null,
  playgoerSex: null,
})
//查询参数

const queryParams = ref({
  accountUsername: '',
  playgoerName: '',
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
    getPlaygoerList()
  },
  onUpdatePageSize: (pageSize) => {
    pagination.pageSize = pageSize
    pagination.page = 1
    getPlaygoerList()
  },
})

//获取戏曲音频评论列表
function getPlaygoerList() {
  if (!loading.value) loading.value = true
  api
    .getPosts({
      pageNum: pagination.page,
      pageSize: pagination.pageSize,
      accountUsername: queryParams.value.accountUsername,
      playgoerName: queryParams.value.playgoerName,
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
  getPlaygoerList()
}

//数据重置
function reset() {
  editForm.accountUsername = ''
  editForm.playgoerBirthday = null
  editForm.playgoerId = ''
  editForm.playgoerInfo = null
  editForm.playgoerName = null
  editForm.playgoerSex = null
}

//修改评论信息
const handleEdit = (row) => {
  reset()
  editForm.playgoerId = row.playgoerId
  editForm.accountUsername = row.accountUsername
  editForm.playgoerBirthday = row.playgoerBirthday
  editForm.playgoerInfo = row.playgoerInfo
  editForm.playgoerName = row.playgoerName
  editForm.playgoerSex = row.playgoerSex

  dialogVisible.value = true
}
const handleUpdateStatue = (playgoerId) => {
  $dialog.confirm({
    content: '确认更改？',
    confirm() {
      api.updateAccountStatue(playgoerId).then((res) => {
        if (res.code === 200 && res.data === true) {
          $message.success('更改成功')
          getPlaygoerList()
        } else {
          $message.error('更改失败')
        }
      })
    },
  })
}
const handleUpdate = () => {
  let formData = new FormData()
  formData.append('playgoerName', editForm.playgoerName)
  formData.append('playgoerInfo', editForm.playgoerInfo)
  formData.append('playgoerBirthday', editForm.playgoerBirthday)
  formData.append('playgoerSex', editForm.playgoerSex)

  formData.append('playgoerId', editForm.playgoerId)
  //修改
  api.updatePost(formData).then((res) => {
    if (res.code === 200 && res.data === true) {
      $message.success('修改成功')
      dialogVisible.value = false
      getPlaygoerList()
    } else {
      $message.error('修改失败')
    }
  })
}
const handleDetail = () => {
  console.log('详情')
}

getPlaygoerList()
</script>
