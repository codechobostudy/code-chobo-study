package io.codechobostudy.sample.repository

import io.codechobostudy.sample.domain.SampleDomain
import org.springframework.data.jpa.repository.JpaRepository
import java.lang.Long
import java.util.List

trait SampleRepository extends JpaRepository[SampleDomain, Long]{

  def findByCategoryName(categoryName: String) : List[SampleDomain]

}
