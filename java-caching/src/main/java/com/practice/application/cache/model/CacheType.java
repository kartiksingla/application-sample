package com.practice.application.cache.model;

public enum CacheType {

	TIME_TO_LIVE {
		
		@Override
		public <IN, OUT> OUT visit(CacheTypeVisitor<IN, OUT> visitor, IN in) {
			return visitor.visitTimeToLive(in);
		}
	},
	SIZE_BASED {
		
		@Override
		public <IN, OUT> OUT visit(CacheTypeVisitor<IN, OUT> visitor, IN in) {
			return visitor.visitSizeBased(in);
		}
	};

	public abstract <IN, OUT> OUT visit(CacheTypeVisitor<IN, OUT> visitor, IN in);
	
//	public <IN, OUT> OUT visit(CacheTypeVisitor<IN, OUT> visitor) {
//		return visit(visitor, null);
//	}
}
