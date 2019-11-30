<template>
	<view>
		<van-cell-group v-if="!modified">
			<van-cell title="公司名" icon="coupon-o" :value="userInfo.company.name"/>
			<van-cell title="公式标签" icon="bookmark-o" >
				<van-tag type="danger">{{userInfo.company.domain}}</van-tag>/ 
				<van-tag type="primary">{{userInfo.company.forms}}</van-tag>
			</van-cell>
			<van-cell title="公司简介" icon="comment-o" :label="userInfo.company.description"/>
		</van-cell-group>
		<van-cell-group v-else>
			<van-cell title="公司名" icon="coupon-o" :value="mdfCompany.name"/>
			<van-cell title="公式标签" icon="bookmark-o" >
				<van-tag type="danger">{{mdfCompany.forms}}</van-tag>/ 
				<van-tag type="primary">{{mdfCompany.domain}}</van-tag>
			</van-cell>
			<van-cell title="公司简介" icon="comment-o" :label="mdfCompany.description"/>
		</van-cell-group>
		<van-button type="primary" size="large" @click="showMdfPopup()">修改信息</van-button>
		<van-button type="info" size="large" @click="submit()" v-if="modified">提交修改</van-button>
		<van-button type="warning" size="large" @click="cancelMdf()" v-if="modified">取消修改</van-button>
		<van-popup v-model="showMdf" position="bottom">
			<van-cell-group>
			  <van-field v-model="mdfCompany.name" required label="公司名" placeholder="请输入公司名"/>
			  <van-field v-model="mdfCompany.forms" label="企业类型" left-icon="user-circle-o" placeholder="如独资企业 合资企业 公司制企业" />
			  <van-field v-model="mdfCompany.domain" label="企业领域" left-icon="records" placeholder="如农业 互联网 汽车等"/>
			  <van-field v-model="mdfCompany.description" required rows="1" autosize label="公司简介" type="textarea" placeholder="请输入简介"/>
			</van-cell-group>
			<van-button type="info" size="large" @click="confirmMdf()">确认</van-button>
		</van-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userInfo: {company:{name:""}},
				mdfCompany: {name:"",forms:"",domain:"",description:""},
				showMdf: false,
				modified: false
			}
		},
		onLoad() {
			var rp = require('request-promise');
			var options = {
			    method: 'POST',
			    uri: 'http://localhost:8080/getUserById',
			    form: {
			        id: this.$cookies.get("userInfo").id,
			    }
			};
			rp(options).then(res => {
				this.$toast.clear();
				if(res == "error") {
					this.$toast.fail('获取用户信息识别，未找到用户信息');
				} else {
					this.userInfo = JSON.parse(res);
					console.log(this.userInfo)
					this.$toast.success('获取用户信息成功');
				}
				// console.log(this.note.backgroundImage)
			}).catch(err => {
				this.$toast.clear();
				this.$toast.fail('获取用户信息, 请检查网络连接');
				console.log(err);
			});
		},
		methods: {
			showMdfPopup() {
				if(this.mdfCompany.name == ""){
					this.mdfCompany.name = this.userInfo.company.name;
					this.mdfCompany.forms = this.userInfo.company.forms;
					this.mdfCompany.domain = this.userInfo.company.domain;
					this.mdfCompany.description = this.userInfo.company.description;
				}
				this.showMdf = true;
			},
			confirmMdf() {
				this.modified = true;
				this.$forceUpdate();
				this.showMdf = false;
			},
			cancelMdf() {
				this.mdfCompany.name = this.userInfo.company.name;
				this.mdfCompany.forms = this.userInfo.company.forms;
				this.mdfCompany.domain = this.userInfo.company.domain;
				this.mdfCompany.description = this.userInfo.company.description;
				this.modified = false;
				this.$forceUpdate();
			},
			submit() {
				this.$toast.loading({
					duration: 0,	// 持续展示 toast
					forbidClick: true,	// 禁用背景点击
					message: '提交中'
				});
				if(this.mdfCompany.name == this.userInfo.company.name) {
					this.mdfCompany.name = "null";
				}
				if(this.mdfCompany.forms == this.userInfo.company.forms) {
					this.mdfCompany.forms = "null";
				}
				if(this.mdfCompany.domain == this.userInfo.company.domain) {
					this.mdfCompany.domain = "null";
				}
				if(this.mdfCompany.description == this.userInfo.company.description) {
					this.mdfCompany.description = "null";
				}
				// console.log(this.mdfCompany);
				var rp = require('request-promise');
				var options = {
					method: 'POST',
					uri: 'http://localhost:8080/updateCompany',
					form: {
						id: this.userInfo.company.id,
						name: this.mdfCompany.name,
						forms: this.mdfCompany.forms,
						domain: this.mdfCompany.domain,
						description: this.mdfCompany.description
					}
				};
				rp(options).then(res => {
					this.$toast.clear();
					if(res == "error") {
						this.$toast.fail('提交失败，数据格式错误');
					} else {
						this.modified = false;
						this.$toast.success('提交成功');
					}
				}).catch(err => {
					this.$toast.success('提交失败，请检查网络连接');
					console.log(err)
				});
				
				
			}
		}
	}
</script>

<style>

</style>
