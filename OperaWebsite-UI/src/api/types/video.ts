export interface OperaVideo {
	/**
	 * 戏曲编号
	 */
	videoId?: number
	/**
	 * 戏曲简介
	 */
	videoInfo?: string
	/**
	 * 类型编号
	 */
	typeId?: number
	/**
	 * 戏曲音频下载量
	 */
	downloadNum?: number
	/**
	 * 视频文件名称
	 */
	filename?: string
	/**
	 * 是否审核
	 */
	isExamine?: number
	/**
	 * 是否删除 （默认0 未删除 1 已删除）
	 */
	isDelete?: number
	/**
	 * 创建时间
	 */
	createdAt?: Date
	/**
	 * 更新时间
	 */
	updatedAt?: Date
	/**
	 * 创建人
	 */
	createdBy?: number
	/**
	 * 修改人
	 */
	updatedBy?: number
}