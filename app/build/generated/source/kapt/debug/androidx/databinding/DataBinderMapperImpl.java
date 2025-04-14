package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new com.tools.edge.dynamic.island.DataBinderMapperImpl());
  }
}
