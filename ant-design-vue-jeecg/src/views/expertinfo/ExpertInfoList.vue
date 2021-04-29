<template>
  <a-card class="j-inner-table-wrapper" :bordered="false">

    <!-- 查询区域 begin -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="名称">
          <a-input v-model="queryParam.name" placeholder="请输入名称"/>
        </a-form-item>
      </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="GR">
          <a-input v-model="queryParam.gr" placeholder="请输入GR"/>
        </a-form-item>
      </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="工作单位">
            <a-input v-model="queryParam.workUnits" placeholder="请输入工作单位"/>
          </a-form-item>
        </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span class="table-page-search-submitButtons table-operator">
              <a-button type="primary" icon="search" @click="searchQuery">查询</a-button>
              <a-button type="primary" icon="reload" @click="searchReset">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                <span>{{ toggleSearchStatus ? '收起' : '展开' }}</span>
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域 end -->

    <!-- 操作按钮区域 begin -->
    <div class="table-operator">
      <a-button type="primary" icon="plus" @click="handleAdd">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('专家管理人员表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>

      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel">
            <a-icon type="delete"/>
            <span>删除</span>
          </a-menu-item>
        </a-menu>
        <a-button>
          <span>批量操作</span>
          <a-icon type="down"/>
        </a-button>
      </a-dropdown>
    </div>
    <!-- 操作按钮区域 end -->

    <!-- table区域 begin -->
    <div>

      <a-alert type="info" showIcon style="margin-bottom: 16px;">
        <template slot="message">
          <span>已选择</span>
          <a style="font-weight: 600;padding: 0 4px;">{{ selectedRowKeys.length }}</a>
          <span>项</span>
          <a style="margin-left: 24px" @click="onClearSelected">清空</a>
        </template>
      </a-alert>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        class="j-table-force-nowrap"
        :loading="loading"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :expandedRowKeys="expandedRowKeys"
        :rowSelection="{selectedRowKeys, onChange: onSelectChange}"
        @expand="handleExpand"
        @change="handleTableChange"
      >

        <!-- 内嵌table区域 begin -->
        <template slot="expandedRowRender" slot-scope="record">
          <a-tabs tabPosition="top">
            <a-tab-pane tab="专家管理变更记录表" key="expertChange" forceRender>
              <expert-change-sub-table :record="record"/>
            </a-tab-pane>
          </a-tabs>
        </template>
        <!-- 内嵌table区域 end -->

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>

        <template slot="imgSlot" slot-scope="text">
          <div style="font-size: 12px;font-style: italic;">
            <span v-if="!text">无图片</span>
            <img v-else :src="getImgView(text)" alt="" style="max-width:80px;height:25px;"/>
          </div>
        </template>

        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            ghost
            type="primary"
            icon="download"
            size="small"
            @click="uploadFile(text)"
          >
            <span>下载</span>
          </a-button>
        </template>

        <template slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical"/>
          <a-dropdown>
            <a class="ant-dropdown-link">
              <span>更多 <a-icon type="down"/></span>
            </a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>

        </template>

      </a-table>
    </div>
    <!-- table区域 end -->

    <!-- 表单区域 -->
    <expert-info-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import ExpertInfoModal from './ExpertInfoModal'
  import ExpertChangeSubTable from './ExpertChangeSubTable'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import '@/assets/less/TableExpand.less'


  export default {
    name: 'ExpertInfoList',
    mixins: [JeecgListMixin],
    components: {
      ExpertInfoModal,
      ExpertChangeSubTable,
    },
    data() {
      return {
        description: '专家管理人员表列表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            key: 'rowIndex',
            width: 60,
            align: 'center',
            customRender: (t, r, index) => parseInt(index) + 1
          },
          {
            title: '名称',
            align: 'center',
            dataIndex: 'name',
          },
          {
            title: '性别',
            align: 'center',
            dataIndex: 'sex_dictText'
          },
          {
            title: '职称',
            align: 'center',
            dataIndex: 'titleOf',
          },
          {
            title: 'GR',
            align: 'center',
            dataIndex: 'gr',
          },
          {
            title: '工作单位',
            align: 'center',
            dataIndex: 'workUnits',
          },
          {
            title: '邮箱',
            align: 'center',
            dataIndex: 'email',
          },
          {
            title: '手机号',
            align: 'center',
            dataIndex: 'mobilePhone',
          },
          {
            title: '用户名',
            align: 'center',
            dataIndex: 'userName',
          },
          {
            title: '技术领域',
            align: 'center',
            dataIndex: 'technicalField_dictText'
          },
          {
            title: '出生日期',
            align: 'center',
            dataIndex: 'birthDate',
          },
          {
            title: '座机',
            align: 'center',
            dataIndex: 'landline',
          },
          {
            title: '外语水平',
            align: 'center',
            dataIndex: 'foreignLanguageLevel_dictText'
          },
          {
            title: '护照类型',
            align: 'center',
            dataIndex: 'passportType_dictText'
          },
          {
            title: '护照号码',
            align: 'center',
            dataIndex: 'passportNum',
          },
          {
            title: '失效日期',
            align: 'center',
            dataIndex: 'expiryDate',
          },
          {
            title: '邮编',
            align: 'center',
            dataIndex: 'postcode',
          },
          {
            title: '单位地址',
            align: 'center',
            dataIndex: 'unitAddress',
          },
          {
            title: '附件',
            align: 'center',
            dataIndex: 'files',
            scopedSlots: {customRender: 'fileSlot'}
          },
          {
            title: '审批结果',
            align: 'center',
            dataIndex: 'approvalResult_dictText'
          },
          {
            title: '审批备注',
            align: 'center',
            dataIndex: 'approvalNote',
          },
          {
            title: '操作',
            dataIndex: 'action',
            align: 'center',
            scopedSlots: { customRender: 'action' },
          },
        ],
        // 字典选项
        dictOptions: {},
        // 展开的行
        expandedRowKeys: [],
        url: {
          list: '/expertinfo/expertInfo/list',
          delete: '/expertinfo/expertInfo/delete',
          deleteBatch: '/expertinfo/expertInfo/deleteBatch',
          exportXlsUrl: '/expertinfo/expertInfo/exportXls',
          importExcelUrl: '/expertinfo/expertInfo/importExcel',
        },
      }
    },
    computed: {
      importExcelUrl() {
        return window._CONFIG['domianURL'] + this.url.importExcelUrl
      }
    },
    methods: {

      initDictConfig() {
      },

      handleExpand(expanded, record) {
        this.expandedRowKeys = []
        if (expanded === true) {
          this.expandedRowKeys.push(record.id)
        }
      },
    }
  }
</script>
<style lang="less" scoped>
  @import '~@assets/less/common.less';
</style>