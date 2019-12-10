<template>
	<view>
		<view v-if="(investigates.length - (currentPage * 5 - 5)) < 5">
			<van-cell v-for="item of investigates.length % 5" is-link v-bind:key="item" :title="investigates[item+listBg - 1].name" :label="investigates[item+listBg-1].description" clickable @click="toInvDetails(item+listBg-1)" />
		</view>
		<view v-else>
			<van-cell v-for="item of 5" v-bind:key="item" is-link :title="investigates[item+listBg - 1].name" :label="investigates[item+listBg-1].description" clickable @click="toInvDetails(item+listBg-1)" />
		</view>
		<van-pagination  v-model="currentPage" :total-items="investigates.length" :items-per-page="5" @change="onPaginationChanged()"/>
		<van-picker show-toolbar title="索引方式" :columns="columns" @cancel="onCancel" @confirm="onConfirm"/>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				investigates: [],
				currentPage: 1,
				listBg: 0,
				columns: ['时间','参与人数']
			}
		},
		onLoad() {
			this.$toast.loading({
				duration: 0,	// 持续展示 toast
				forbidClick: true,	// 禁用背景点击
				message: '加载中'
			});
			var rp = require('request-promise');
			rp('http://101.201.70.76:8211/getVisibleInv').then(res => {	// 请求获取所有可见问卷
				this.$toast.clear();
				if(res == "error") {
					this.$toast.fail('获取问卷失败，未找到问卷信息');
				} else {
					this.investigates = JSON.parse(res);
					// console.log(this.investigates);
					this.$toast.success('加载成功');
				}
				// console.log(this.note.backgroundImage)
			}).catch(err => {	// 请求失败
				this.$toast.clear();
				this.$toast.fail('获取问卷信息, 请检查网络连接');
				// console.log(err);
			});
		},
		methods: {
			onPaginationChanged() {	// 翻页函数
				this.listBg = this.currentPage * 5 - 5;
			},
			toInvDetails(key) {	// 根据问卷id跳转至对应答题页
				this.$router.push({	
					path: '/pages/investigates/involvedInv', 
					query: {
						invDetailsId: this.investigates[key].id
					}
				});
			},
			 onConfirm(value, index) {	// 排序函数
				// console.log(value);
				if(value == "时间") {	// 根据时间排序
					this.investigates.sort(function(a,b){
					return a.time>b.time});
				}else if(value == "参与人数") {	// 根据参与人数排序
					this.investigates.sort(function(a,b){
					return a.results.length < b.results.length});
				}
				this.$forceUpdate();
				
			}
		}
	}
</script>

<style>
</style>
