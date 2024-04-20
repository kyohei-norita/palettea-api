package palettea.api.infrastructure.domain.paint;

import lombok.NonNull;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.UUID;

@Mapper
public interface PaintPgMapper {

  @Insert("""
    INSERT INTO palettea.paint(
      paint_id,
      name,
      rgb,
      coatingtype
    ) VALUES(
      #{input.id},
      #{input.paintName},
      #{input.rgb},
      #{input.coatingType}
     );
    """)
  void insert(@Param("input") InsertInput input);

  record InsertInput(
    @NonNull UUID id,
    @NonNull String paintName,
    @NonNull String rgb,
    @NonNull String coatingType
  ) {}
}
