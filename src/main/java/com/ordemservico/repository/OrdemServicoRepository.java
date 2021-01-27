package com.ordemservico.repository;

import com.ordemservico.domain.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {


}
