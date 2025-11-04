node 'default' { 
  include apache
}

class apache { 
  package { 'apache2':
    ensure => present
  }

  service { 'apache2':
    require => Package['apache2'],  
    ensure => running,
    enable => true, 
  }

  file { '/var/www/html': 
    require => Package['apache2'],  
    target => '/vagrant/www', 
    ensure => link,
    force => true 
  }
}
