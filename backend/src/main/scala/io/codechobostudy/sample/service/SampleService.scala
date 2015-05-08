package io.codechobostudy.sample.service

import io.codechobostudy.sample.domain.SampleDomain
import io.codechobostudy.sample.repository.SampleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SampleService {

  @Autowired
  private var categoryRepository: SampleRepository = _

  def create(category: SampleDomain): SampleDomain = {
    categoryRepository.save(category)
  }

  def update(category: SampleDomain): SampleDomain = {
    val updateCategory = categoryRepository.findOne(category.getId)
    updateCategory.setCategoryName(category.categoryName)
    updateCategory.setCategoryDesc(category.categoryDesc)

    categoryRepository.save(updateCategory)
  }

  def getAllCategory: java.util.List[SampleDomain] = {
    categoryRepository.findAll()
  }
}
