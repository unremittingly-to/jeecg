<template>
  <j-modal
    :title="title"
    :width="1200"
    :visible="visible"
    :maskClosable="false"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel">
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>

          <a-col :xs="24" :sm="12">
            <a-form-item label="名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['name']" placeholder="请输入名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="性别" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['sex']" :trigger-change="true" dictCode="sex" placeholder="请选择性别"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="职称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['titleOf']" placeholder="请输入职称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="GR" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['gr']" placeholder="请输入GR"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="工作单位" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['workUnits']" placeholder="请输入工作单位"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="邮箱" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['email', validatorRules.email]" placeholder="请输入邮箱"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="手机号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['mobilePhone', validatorRules.mobilePhone]" placeholder="请输入手机号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="用户名" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['userName']" placeholder="请输入用户名"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="技术领域" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-multi-select-tag type="checkbox" v-decorator="['technicalField']" :trigger-change="true" dictCode="thchnical_field" placeholder="请选择技术领域"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="出生日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择出生日期" v-decorator="['birthDate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="座机" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['landline', validatorRules.landline]" placeholder="请输入座机"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="外语水平" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['foreignLanguageLevel']" :trigger-change="true" dictCode="foreign_language_level" placeholder="请选择外语水平"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="护照类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="list" v-decorator="['passportType']" :trigger-change="true" dictCode="passport_type" placeholder="请选择护照类型"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="护照号码" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['passportNum']" placeholder="请输入护照号码"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="失效日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择失效日期" v-decorator="['expiryDate']" :trigger-change="true" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="邮编" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['postcode', validatorRules.postcode]" placeholder="请输入邮编"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="单位地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['unitAddress']" placeholder="请输入单位地址"></a-input>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="附件" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-upload v-decorator="['files']" :trigger-change="true"></j-upload>
            </a-form-item>
          </a-col>
          <a-col :xs="24" :sm="12">
            <a-form-item label="审批结果" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-dict-select-tag type="radio" v-decorator="['approvalResult']" :trigger-change="true" dictCode="approval_result" placeholder="请选择审批结果"/>
            </a-form-item>
          </a-col>
          <a-col :span="24">
            <a-form-item label="审批备注" :labelCol="labelCol2" :wrapperCol="wrapperCol2">
              <a-textarea v-decorator="['approvalNote']" rows="4" placeholder="请输入审批备注"/>
            </a-form-item>
          </a-col>

        </a-row>
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="专家管理变更记录表" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="expertChangeTable.loading"
            :columns="expertChangeTable.columns"
            :dataSource="expertChangeTable.dataSource"
            :maxHeight="300"
            :rowNumber="true"
            :rowSelection="true"
            :actionButton="true"/>
        </a-tab-pane>
        
      </a-tabs>

    </a-spin>
  </j-modal>
</template>

<script>

  import pick from 'lodash.pick'
  import { FormTypes,getRefPromise } from '@/utils/JEditableTableUtil'
  import { JEditableTableMixin } from '@/mixins/JEditableTableMixin'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'  
  import JUpload from '@/components/jeecg/JUpload'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  import JMultiSelectTag from "@/components/dict/JMultiSelectTag"

  export default {
    name: 'ExpertInfoModal',
    mixins: [JEditableTableMixin],
    components: {
      JDate,
      JUpload,
      JDictSelectTag,
      JMultiSelectTag,
    },
    data() {
      return {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 6 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        labelCol2: {
          xs: { span: 24 },
          sm: { span: 3 },
        },
        wrapperCol2: {
          xs: { span: 24 },
          sm: { span: 20 },
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
          email: {
            rules: [
              { pattern: /^([\w]+\.*)([\w]+)@[\w]+\.\w{3}(\.\w{2}|)$/, message: '请输入正确的电子邮件!'},
            ]
          },
          mobilePhone: {
            rules: [
              { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码!'},
            ]
          },
          landline: {
            rules: [
              { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码!'},
            ]
          },
          postcode: {
            rules: [
              { pattern: /^[1-9]\d{5}$/, message: '请输入正确的邮政编码!'},
            ]
          },
        },
        refKeys: ['expertChange', ],
        tableKeys:['expertChange', ],
        activeKey: 'expertChange',
        // 专家管理变更记录表
        expertChangeTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '变更字段',
              key: 'changeField',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '以前内容',
              key: 'beforeContent',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '变更后内容',
              key: 'afterContent',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '变更属性',
              key: 'changeAttribute',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
            {
              title: '专家管理id',
              key: 'expertId',
              type: FormTypes.input,
              width:"200px",
              placeholder: '请输入${title}',
              defaultValue: '',
            },
          ]
        },
        url: {
          add: "/expertinfo/expertInfo/add",
          edit: "/expertinfo/expertInfo/edit",
          expertChange: {
            list: '/expertinfo/expertInfo/queryExpertChangeByMainId'
          },
        }
      }
    },
    methods: {
      getAllTable() {
        let values = this.tableKeys.map(key => getRefPromise(this, key))
        return Promise.all(values)
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        let fieldval = pick(this.model,'name','sex','titleOf','gr','workUnits','email','mobilePhone','userName','technicalField','birthDate','landline','foreignLanguageLevel','passportType','passportNum','expiryDate','postcode','unitAddress','files','approvalResult','approvalNote')
        this.$nextTick(() => {
          this.form.setFieldsValue(fieldval)
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.expertChange.list, params, this.expertChangeTable)
        }
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)

        return {
          ...main, // 展开
          expertChangeList: allValues.tablesValue[0].values,
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },
     popupCallback(row){
       this.form.setFieldsValue(pick(row,'name','sex','titleOf','gr','workUnits','email','mobilePhone','userName','technicalField','birthDate','landline','foreignLanguageLevel','passportType','passportNum','expiryDate','postcode','unitAddress','files','approvalResult','approvalNote'))
     },

    }
  }
</script>

<style scoped>
</style>