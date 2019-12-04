<template>
	<view>
		<view v-for="(item, key) in users" :key="key">
			<van-panel :title="item.username" :desc="item.name" :status="item.email">
			  <img :src="'data:image/png;base64,'+item.avatar" class='detail-img'/>
			  <div slot="footer">
			    <van-button size="small" type="danger" @click="showSendEmail(key)">发送邮件</van-button>
				<van-button size="small" type="info" @click="userEOP(key)">提升权限</van-button>
			  </div>
			</van-panel>
			
		</view>
		<van-popup v-model="showEmail" position="bottom" :style="{ height: '50%' }" >
			<van-field v-model="emailTitle" label="邮件标题" placeholder="请输入邮件标题"/>
			<van-field v-model="emailBody" rows="1" autosize label="邮件内容" type="textarea" placeholder="请输入邮件内容"/>
			<van-button size="large" type="info" @click="sendEmail()">发送</van-button>
		</van-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				users: [],
				showEmail: false,
				emailBody: "",
				emailTitle: "",
				nowKey: 0
			}
		},
		onLoad() {
			this.$toast.loading({
				duration: 0,	// 持续展示 toast
				forbidClick: true,	// 禁用背景点击
				message: '加载中'
			});
			var rp = require('request-promise');
			var options = {	// 根据id获取用户包括头像与人脸数据的全部信息
			    method: 'POST',
			    uri: 'http://101.201.70.76:8211/getUsers'
			};
			rp(options).then(res => {	// 请求成功
				this.$toast.clear();
				if(res == "error") {
					this.$toast.fail('加载失败，未找到用户信息');
				} else {
					this.users = JSON.parse(res);
					console.log(this.users);
					this.$toast.success('加载成功');
				}
				// console.log(this.note.backgroundImage)
			}).catch(err => {	// 请求失败
				this.$toast.clear();
				this.$toast.fail('加载失败, 请检查网络连接');
				console.log(err);
			});
		},
		methods: {
			showSendEmail(key) {
				this.nowKey = key;
				this.emailTitle = "";
				this.emailBody = "";
				this.showEmail = true;
			},
			sendEmail() {
				this.$toast.loading({
					duration: 0,	// 持续展示 toast
					forbidClick: true,	// 禁用背景点击
					message: '发送中'
				});
				// console.log("发送邮件");
				// console.log(this.nowKey);
				// console.log(this.users[this.nowKey].email);
				var rp = require('request-promise');
				var options = {	// 根据id获取用户包括头像与人脸数据的全部信息
				    method: 'POST',
				    uri: 'http://101.201.70.76:8211/sendEmail',
					form: {
					    email: this.users[this.nowKey].email,
						title: this.emailTitle,
						body: this.emailBody
					}
				};
				rp(options).then(res => {	// 请求成功
					this.showEmail = false;
					console.log(res);
					this.$toast.clear();
					this.$toast.success('发送成功');

					// console.log(this.note.backgroundImage)
				}).catch(err => {	// 请求失败
					this.$toast.clear();
					this.$toast.fail('发送失败, 请检查网络连接');
					console.log(err);
				});
			},
			userEOP(key) {
				this.$toast.loading({
					duration: 0,	// 持续展示 toast
					forbidClick: true,	// 禁用背景点击
					message: '提交中'
				});

				var rp = require('request-promise');
				var options = {	// 提升用户权限
				    method: 'POST',
				    uri: 'http://101.201.70.76:8211/userEOP',
					form: {
						id: this.users[key].id,
						power: 2
					}
				};
				
				rp(options).then(res => {	// 请求成功
					// console.log(res);
					this.$toast.clear();
					this.$toast.success('提升成功');
				}).catch(err => {	// 请求失败
					this.$toast.clear();
					this.$toast.fail('提升失败');
					// console.log(err);
				});
			}
		}
	}
</script>

<style>
	.detail-img {
		width: 280rpx;
		height: 280rpx;
		margin-left: 24rpx;
	}
</style>
