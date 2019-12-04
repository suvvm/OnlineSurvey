<template>
	<view>
		<van-steps :active="active">
			<van-step>注册账号</van-step>
			<van-step>选择角色</van-step>
			<van-step>公司注册</van-step>
			<van-step>注册完成</van-step>
		</van-steps>
		
		<van-cell-group>
			<van-field
				v-model="name"
				label="公司名"
				left-icon="friends-o"
				placeholder="请输入公司名"
			/>
			<van-field
				v-model="forms"
				label="企业类型"
				left-icon="user-circle-o"
				placeholder="如独资企业 合资企业 公司制企业"
			/>
			<van-field
				v-model="domain"
				label="企业领域"
				left-icon="records"
				placeholder="如农业 互联网 汽车等"
			/>
			<van-field
				v-model="description"
				label="简介"
				type="textarea"
				left-icon="other-pay"
				placeholder="简介"
				maxlength="200"
			/>		
		</van-cell-group>
		<van-button type="primary" size="large" @click="submit">提交</van-button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				active : 2,
				name: '',
				forms: '',
				domain: '',
				description: '',
				userInfo: {},
			}
		},
		onLoad() {
			if(this.$cookies.get("userInfo") != null){
				this.userInfo = this.$cookies.get("userInfo");
			}
		},
		methods: {
			submit() {
				this.$toast.loading({
					duration: 0,	// 持续展示 toast
					forbidClick: true,	// 禁用背景点击
					message: '提交中'
				});
				
				if(this.name == '' || this.forms == '' || this.domain == '' || this.description == '') {
					this.$toast.clear();
					this.$toast.fail('请填写必填信息');
					return;
				}
				if(this.userInfo == null) {
					this.$toast.clear();
					this.$toast.fail('为找到用户');
					return;
				}
				
				var rp = require('request-promise');
				var options = {
				    method: 'POST',
				    uri: 'http://101.201.70.76:8211/insertCmp',
				    form: {
				        name: this.name,
						forms: this.forms,
						domain: this.domain,
						description: this.description,
						ownerid: this.userInfo.id
				    }
				};
				rp(options).then(res => {
					this.userInfo.power = 1;
					this.$toast.clear();
					this.$cookies.set("userInfo", this.userInfo, 60 * 60  * 24 * 7);
					this.$toast.success('提交成功');
					this.$router.push({
						path: '/pages/register/registerCompleted', 
					});
					console.log(res)
				}).catch(err => {
					this.$toast.clear();
					this.$toast.fail('提交失败，网络连接出错');
					console.log(err)
				});
			}
		}
	}
</script>

<style>
</style>
