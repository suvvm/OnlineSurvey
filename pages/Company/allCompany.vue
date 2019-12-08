<template>
	<view>
		<!-- 遍历公司列表显示公司信息 -->
		<van-cell-group v-for="(item, key) in companies" :key="key">
			<van-cell title="公司名" icon="coupon-o" :value="item.name"/>
			<van-cell title="公式标签" icon="bookmark-o" >
				<van-tag type="danger">{{item.forms}}</van-tag>/ 
				<van-tag type="primary">{{item.domain}}</van-tag>
			</van-cell>
			<van-cell title="公司简介" icon="comment-o" :label="item.description"/>
		</van-cell-group>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				companies: []
			}
		},
		onLoad() {
			this.$toast.loading({
				duration: 0,	// 持续展示 toast
				forbidClick: true,	// 禁用背景点击
				message: '加载中'
			});
			var rp = require('request-promise');
			var options = {	// 获取全部公司
			    method: 'POST',
			    uri: 'http://101.201.70.76:8211/getCompanies'
			};
			rp(options).then(res => {	// 请求成功
				this.$toast.clear();
				if(res == "error") {
					this.$toast.fail('获取公司信息失败，未找到公司信息');
				} else {
					this.companies = JSON.parse(res);	// 以得到的json串构造公司列表
					// console.log(this.companies);
					this.$toast.success('加载成功');
				}
				// console.log(this.note.backgroundImage)
			}).catch(err => {	// 请求失败
				this.$toast.clear();
				this.$toast.fail('加载失败, 请检查网络连接');
				// console.log(err);
			});
		},
		methods: {
			
		}
	}
</script>

<style>
</style>
