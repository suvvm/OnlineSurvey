<template>
	<view class=''>
		<!-- 以头像作为底部背景，在上方加遮罩层来美化界面 -->
		<view class='detail-container' :style ="'background:url(data:image/png;base64,'+userInfo.avatar + ') no-repeat  center/cover'"></view>
		<!--style='background: url(/testUserInfo/imgs/avatar.jpg) no-repeat  top/cover' -->
		<view class='detail-mask'></view>
		<!-- 显示用户基本信息 -->
		<view class='detail-info'>
			<img :src="'data:image/png;base64,'+userInfo.avatar" class='detail-img'/>
			<!-- <img :src="'data:image/png;base64,'+imgfile" class="avatar"/> -->
			<view class='detail'>
				<view class='detail-nm'>{{userInfo.username}}</view>
				<view>姓名：{{userInfo.name}}</view>
				<view class='detail.sc'>电话：{{userInfo.pnum}}</view>
				<view>邮箱：{{userInfo.email}}</view>
				<view v-if="userInfo.gender == 0">性别：男</view> 
				<view v-else>性别：女</view> 
			</view>
		</view>
		
		<!-- 判断公司用户 管理员 普通用户后具体实现 -->
		<!-- 管理员用户 -->
		<van-cell-group v-if="userInfo.power == 2">
			<van-cell title="审核调查" icon="todo-list-o" is-link @click="toInvolvedInv()"/>
			<van-cell title="管理公司" icon="friends-o" is-link />
			<van-cell title="管理标签" icon="friends-o" is-link @click="toMdfTag()"/>
			<van-cell title="管理用户" icon="user-o" is-link />
		</van-cell-group>
		<!-- 公司用户 -->
		<van-cell-group v-if="userInfo.power == 1">
			<van-cell title="发布调查" icon="todo-list-o" is-link @click="toCreateInv()" />
			<van-cell title="我的公司" icon="friends-o" is-link @click="toMyCmp()"/>
			<van-cell title="管理调查" icon="orders-o" is-link @click="toCmpInv()"/>
		</van-cell-group>
		<!-- 普通用户 -->
		<van-cell-group v-if="userInfo.power == 0">
			<van-cell title="我的调查" icon="description" is-link />
		</van-cell-group>
		<!-- 全体用户通用 -->
		<van-cell-group>
			<van-cell title="修改个人信息" icon="records" is-link @click="toMdfUserInf()"/>
		</van-cell-group>
		<!-- 非管理员用户通用 -->
		<van-cell-group v-if="userInfo.power != 2">
			<van-cell title="关于我们" icon="chat-o" is-link @click="toAboutUs()"/>
		</van-cell-group>
		<!-- 全体用户通用 -->
		<van-cell-group>
			<van-cell title="退出登录" icon="down" is-link @click="logout()"/>
		</van-cell-group>
		<!-- 底部导航栏 -->
		<van-tabbar route>
		  <van-tabbar-item icon="home-o" to="/pages/home/home">首页</van-tabbar-item>
		  <van-tabbar-item icon="search" to="/pages/search/search">检索</van-tabbar-item>
		  <van-tabbar-item icon="friends-o" to="/pages/categories/categories">标签</van-tabbar-item>
		  <van-tabbar-item icon="user-o" to="/pages/user/user">我的</van-tabbar-item>
		</van-tabbar>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userInfo: {avatar:""}
			}
		},
		onLoad() {
			var rp = require('request-promise');
			var options = {	// 根据id获取用户包括头像与人脸数据的全部信息
			    method: 'POST',
			    uri: 'http://localhost:8080/getUserById',
			    form: {
			        id: this.$cookies.get("userInfo").id,
			    }
			};
			rp(options).then(res => {	// 请求成功
				this.$toast.clear();
				if(res == "error") {
					this.$toast.fail('获取用户信息识别，未找到用户信息');
				} else {
					this.userInfo = JSON.parse(res);
					this.$toast.success('获取用户信息成功');
				}
				// console.log(this.note.backgroundImage)
			}).catch(err => {	// 请求失败
				this.$toast.clear();
				this.$toast.fail('获取用户信息, 请检查网络连接');
				console.log(err);
			});
		},
		methods: {
			toCreateInv() {	// 路由至发布调查页
				this.$router.push({
					path: '/pages/investigates/addInvestigates', 
				});
			},
			toMyCmp() {	// 路由至我的公司页
				this.$router.push({
					path: '/pages/Company/userCompany', 
				});
			},
			toMdfUserInf() {	// 路由至修改个人信息页
				this.$router.push({
					path: '/pages/user/mdfUser', 
				});
			},
			toCmpInv() {	// 路由至管理调查页
				this.$router.push({
					path: '/pages/investigates/allCmpInv', 
				});
			},
			toAboutUs() {	// 路由至关于我们页
				this.$router.push({
					path: '/pages/AboutUs/AboutUs', 
				});
			},
			toInvolvedInv() {	// 路由至审核页
				this.$router.push({
					path: '/pages/investigates/verifyInvestigates', 
				});
			},
			toMdfTag() {	// 路由至管理标签页
				this.$router.push({
					path: '/pages/tag/mdfTag', 
				});
			},
			logout() {	// 推出登录方法
				this.$cookies.remove("userInfo");
				this.$router.push({
					path: '/pages/index/index', 
				});
			}
		}
	}
</script>

<style>
	.test{
		height: 400rpx;
		width: 400rpx;
	}
	.detail-container {
		height: 400rpx;
		filter: blur(40rpx);
		opacity: 0.4;
	}
	  
	.detail-mask {
		position: absolute;
		width: 100%;
		height: 400rpx;
		background-color: #333;
		top: 0;
		left: 0;
		z-index: -1;
	}
	  
	.detail-img {
		width: 280rpx;
		height: 70%;
		margin-right: 24rpx;
	}
	  
	.detail-info {
		display: flex;
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 400rpx;
		padding: 20rpx;
	}
	  
	.detail {
		flex-grow: 1;
		line-height: 60rpx;
	}
	  
	.detail view {
		color: #fff;
		font-size: 24rpx;
	}
	  
	.detail .detail-nm {
		font-size: 40rpx;
		font-weight: 700;
	}
	  
	.detail .sc {
		color: #fc0;
		font-size: 36rpx;
		font-weight: 700;
	}
	  
	.desc {
		padding: 20rpx;
		color: #555;
		font-size: 24rpx;
	}
	  
	.comment-container {
		padding: 0 20rpx;
	}
	  
	.comment {
		padding: 10rpx;
	}
	  
	.comment-content {
		border: 1px solid #ccc;
		width: 100%;
		box-sizing: border-box;
		font-size: 32rpx;
		border-radius: 8rpx;
		padding: 20rpx;
	}
	  
	.comment-image image {
		width: 200rpx;
		height: 200rpx;
		margin: 10rpx;
	}
	  
	.comment-img {
		width: 200rpx;
		height: 200rpx;
		margin: 20rpx 20rpx 0 0;
	}
</style>
