<template>
	<view>
		<van-search
		  v-model="searchValue"
		  placeholder="请输入搜索关键词"
		  show-action
		  shape="round"
		  @search="onSearch"
		>
		  <div slot="action" @click="onSearch">搜索</div>
		</van-search>
		<van-index-bar>
			<van-index-anchor index="A" />
			<van-cell title="文本" />
			<van-cell title="文本" />
			<van-cell title="文本" />
		
			<van-index-anchor index="B" />
			<van-cell title="文本" />
			<van-cell title="文本" />
			<van-cell title="文本" />
		
			<van-index-anchor index="C" />
			<van-cell title="文本" />
			<van-cell title="文本" />
			<van-cell title="文本" />
		 
		</van-index-bar>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				searchValue: ''
				
			}
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
