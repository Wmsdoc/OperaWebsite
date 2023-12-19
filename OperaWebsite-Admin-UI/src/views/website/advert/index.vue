<template>
  <div>
    <CommonPage title="戏曲网站广告">
      <!-- 新增 -->
      <n-space>
        <div>
          <n-button type="primary" class="ml-16" @click="handleAdd">
            <TheIcon icon="material-symbols:add" :size="18" class="mr-5" />
            发布广告
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
          <n-form-item label="广告描述" path="advertContent">
            <n-input
              v-model:value="editForm.advertContent"
              placeholder="请输入广告描述"
              type="textarea"
              maxlength="200"
              show-count
            />
          </n-form-item>
          <n-form-item label="广告链接" path="linkUrl">
            <n-input v-model:value="editForm.linkUrl" placeholder="请输入广告链接" />
          </n-form-item>
          <n-form-item label="广告图片">
            <n-upload
              class="mx-auto w-[75%] p-20 text-center"
              :custom-request="handleUpload"
              :show-file-list="false"
              accept=".png,.jpg,.jpeg"
              @before-upload="onBeforeUpload"
            >
              <n-upload-dragger>
                <div class="h-150 f-c-c flex-col">
                  <TheIcon icon="mdi:upload" :size="68" class="mb-12 c-primary" />
                  <n-text class="text-14 c-gray">点击或者拖动文件到该区域来上传</n-text>
                </div>
              </n-upload-dragger>
            </n-upload>
          </n-form-item>
        </n-form>
        <n-card v-if="imgUrl" class="mt-16 items-center">
          <n-image-group>
            <n-space justify="space-between" align="center">
              <n-card class="w-280 hover:card-shadow">
                <div class="h-160 f-c-c">
                  <n-image width="200" :src="imgUrl" />
                </div>
                <n-space class="mt-16" justify="space-evenly">
                  <n-button dashed type="primary" @click="copy(imgUrl.value)">url</n-button>
                  <n-button
                    dashed
                    type="primary"
                    @click="copy(`&lt;img src=&quot;${imgUrl.value}&quot; /&gt;`)"
                  >
                    img
                  </n-button>
                </n-space>
              </n-card>
            </n-space>
          </n-image-group>
        </n-card>
        <template #action>
          <n-button type="primary" @click="dialogVisible = false">取消</n-button>
          <n-button type="primary" @click="doCreate">确定</n-button>
        </template>
      </n-modal>
    </CommonPage>
  </div>
</template>

<script setup>
import { NButton, NImage, NTag } from 'naive-ui'
import api from './api'
import { h, reactive, ref } from 'vue'
import { renderIcon } from '@/utils'
import { useClipboard } from '@vueuse/core'
defineOptions({ name: 'Upload' })

const { copy, copied } = useClipboard()

const imgUrl = ref()

watch(copied, (val) => {
  val && $message.success('已复制到剪切板')
})

//表格字段
const columns = [
  {
    title: '广告描述',
    key: 'advertContent',
    width: 150,
    ellipsis: { tooltip: true },
    render(row) {
      return h(
        NTag,
        {
          bordered: false,
          type: 'info',
          onClick: () => handleDetail(row),
        },
        { default: () => row.advertContent }
      )
    },
  },
  {
    title: '广告发布时间',
    key: 'advertCreatedAt',
    width: 200,
    ellipsis: { tooltip: true },
  },
  {
    title: '广告链接地址',
    key: 'linkUrl',
    width: 200,
    ellipsis: { tooltip: true },
  },
  {
    title: '广告图片',
    key: 'imgUrl',
    width: 300,
    ellipsis: { tooltip: true },
    render(row) {
      return h(
        NImage,
        {
          width: '200',
          onClick: () => handleDetail(row),
          src: row.imgUrl,
        },
        { default: () => row.imgUrl }
      )
    },
  },
  {
    title: '操作',
    key: 'actions',
    width: 150,
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
            onClick: () => handleDelete(row.advertId),
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
  advertContent: null,
  imgUrl: null,
  linkUrl: null,
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
    getAdvertList()
  },
  onUpdatePageSize: (pageSize) => {
    pagination.pageSize = pageSize
    pagination.page = 1
    getAdvertList()
  },
})

function getAdvertList() {
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

const handleDelete = (advertId) => {
  $dialog.confirm({
    content: '确定删除？',
    confirm() {
      api.deletePost(advertId).then((res) => {
        if (res.code === 200 && res.data === true) {
          console.log(res)
          $message.success('删除成功')
          getAdvertList()
        } else {
          $message.error('删除失败')
        }
      })
    },
  })
}
const doCreate = () => {
  let formData = new FormData()
  formData.append('advertContent', editForm.advertContent)
  formData.append('imgUrl', editForm.imgUrl)
  formData.append('linkUrl', editForm.linkUrl)
  //新增
  api.addPost(formData).then((res) => {
    if (res.code === 200 && res.data === true) {
      $message.success('新增成功')
      dialogVisible.value = false
      getAdvertList()
    } else {
      $message.error('新增失败')
    }
  })
}

const handleAdd = () => {
  editForm.advertContent = null
  editForm.imgUrl = null
  editForm.linkUrl = null
  dialogVisible.value = true
}
const handleDetail = () => {
  console.log('详情')
}

function onBeforeUpload({ file }) {
  if (!file.file?.type.startsWith('image/')) {
    $message.error('只能上传图片')
    return false
  }
  return true
}

async function handleUpload({ file, onFinish }) {
  if (!file || !file.type) {
    $message.error('请选择文件')
  }
  let formData = new FormData()
  formData.append('file', file.file)
  formData.append('fileType', 3)
  // 模拟上传
  $message.loading('上传中...')
  api.upload(formData).then((res) => {
    if (res.code === 200) {
      $message.success('上传成功')
      editForm.imgUrl = res.data
      imgUrl.value = URL.createObjectURL(file.file)
      onFinish()
    } else {
      $message.error('上传失败')
    }
  })

  // $message.success('上传成功')
  //   imgUrl.value = URL.createObjectURL(file.file)
  //   onFinish()
}

getAdvertList()
</script>
